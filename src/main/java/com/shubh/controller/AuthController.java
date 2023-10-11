package com.shubh.controller;
import com.shubh.config.JwtProvider;
import com.shubh.exceptions.userException;
import com.shubh.model.User;
import com.shubh.repository.UserRepository;
import com.shubh.response.AuthResponse;
import com.shubh.request.LoginRequest;
import com.shubh.service.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUser customUser;

    public AuthController(UserRepository userRepository, CustomUser customUser,PasswordEncoder passwordEncoder,JwtProvider jwtProvider){
        this.userRepository=userRepository;
        this.customUser=customUser;
        this.passwordEncoder=passwordEncoder;
        this.jwtProvider=jwtProvider;

    }
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws  userException{
     String email = user.getEmail();
     String password = user.getPassword();
     String firstName= user.getFirstName();
     String lastName = user.getLastname();

     User isEmailExist= userRepository.findByEmail(email);
     if(isEmailExist!=null){
         throw new userException("Email is already exist");
     }

     User createdUser = new User();
     createdUser.setEmail(email);
     createdUser.setPassword(passwordEncoder.encode(password));
     createdUser.setFirstName(firstName);
     createdUser.setLastname(lastName);

     User savedUser = userRepository.save(createdUser);
        Authentication authentication =new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token,"signUp sucessfull");
//        authResponse.setJwt(token);
//        authResponse.setMessage("SignUp successfully");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse>loginHandler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token,"signIn sucessfull");
//        authResponse.setJwt(token);
//        authResponse.setMessage("SignIn successfully");
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails =customUser.loadUserByUsername(username);
        if(userDetails ==null){
            throw new BadCredentialsException("Inavlid Credentials");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid credentials ");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
