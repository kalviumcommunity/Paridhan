package com.shubh.repository;

import com.shubh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    public User findByEmail(String email);
    User findByOauth2Email(String oauth2Email);

}
