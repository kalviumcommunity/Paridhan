package com.shubh.ecommerce.controller;
import com.shubh.ecommerce.config.JwtProvider;
import com.shubh.ecommerce.exceptions.UserException;
import com.shubh.ecommerce.model.Cart;
import com.shubh.ecommerce.service.CartService;
import com.shubh.ecommerce.service.CustomUser;
import com.shubh.ecommerce.user.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.repository.UserRepository;
import com.shubh.ecommerce.request.LoginRequest;
import com.shubh.ecommerce.response.AuthResponse;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtTokenProvider;
    private CustomUser customUserDetails;
     private CartService cartService;

    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtProvider jwtTokenProvider,CustomUser customUserDetails,CartService cartService) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
        this.customUserDetails=customUserDetails;
        this.cartService=cartService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@Valid @RequestBody User user) throws UserException{

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isEmailExist=userRepository.findByEmail(email);

        // Check if user with the given email already exists
        if (isEmailExist!=null) {
            // System.out.println("--------- exist "+isEmailExist).getEmail());

            throw new UserException("Email Is Already Used With Another Account");
        }

        // Create new user
        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setMobile(user.getMobile());
        createdUser.setRole(UserRole.ROLE_USER);
        createdUser.setRole(UserRole.ROLE_ADMIN);


        User savedUser= userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username +" ----- "+password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtTokenProvider.generateToken(authentication);
//        AuthResponse authResponse= new AuthResponse();
//
//        authResponse.setStatus(true);
//        authResponse.setJwt(token);
        AuthResponse authResponse= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails - "+userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
