package com.week4.jwt.service;


import com.week4.jwt.model.User;

public interface UserService {
  boolean updateUser(User userInfo);
  User getUserInfo(int userId);
}
