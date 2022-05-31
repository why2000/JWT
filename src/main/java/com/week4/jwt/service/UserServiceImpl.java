package com.week4.jwt.service;

import com.week4.jwt.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Override public boolean updateUser(User userInfo) {
    return false;
  }

  @Override public User getUserInfoByUsername(String username) {
    return null;
  }
}
