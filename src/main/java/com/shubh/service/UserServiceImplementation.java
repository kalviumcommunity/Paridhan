package com.shubh.service;

import com.shubh.config.JwtProvider;
import com.shubh.exceptions.userException;
import com.shubh.model.User;
import com.shubh.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(long userId) throws userException {
        Optional<User>user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw  new userException("user not found with id:"+ userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws userException {

        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);

        if(user==null){
            throw  new userException("user not found with this "+email);
        }
        return user;
    }
}
