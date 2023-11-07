package com.shubh.ecommerce.service;
import java.util.ArrayList;
import java.util.List;

import com.shubh.ecommerce.model.User;
import com.shubh.ecommerce.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUser implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUser(UserRepository userRepository) {
        this.userRepository=userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("user not found with email "+username);
        }
        System.out.println("Found user: " + user.getEmail());

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

}
