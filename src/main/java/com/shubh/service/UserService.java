package com.shubh.service;
import com.shubh.exceptions.userException;
import com.shubh.model.User;

public interface UserService {

    public User findUserById(Long userId) throws userException;

    public User findUserProfileByJwt(String jwt) throws userException;
}
