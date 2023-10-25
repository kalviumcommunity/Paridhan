package com.shubh.ecommerce.service;
import com.shubh.ecommerce.exceptions.UserException;
import com.shubh.ecommerce.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

}