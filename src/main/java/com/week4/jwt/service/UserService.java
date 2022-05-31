package com.week4.jwt.service;


import com.week4.jwt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  boolean updateUser(User userInfo);
}
