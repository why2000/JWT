package com.week4.jwt.httpModel;

public class LoginRes {
  private final boolean success;
  private final String token;

  public LoginRes(boolean success, String token) {
    this.success = success;
    this.token = token;
  }

  @Override public String toString() {
    return "LoginRes{" + "success=" + success + ", token='" + token + '\'' + '}';
  }
}
