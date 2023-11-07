package com.shubh.ecommerce.repository;

import com.shubh.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);

//@Query("SELECT u FROM User u WHERE u.email = :email")
//User findUserByEmail(@Param("email") String email);
}
