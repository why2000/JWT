package com.week4.jwt.service;

import com.week4.jwt.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Override public boolean updateUser(User userInfo) {
    return false;
  }

  @Override public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    return null;
  }
}
