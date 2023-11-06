package com.shubh.ecommerce.repository;

import com.shubh.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    public User findByEmail(String email);


}