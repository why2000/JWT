package com.week4.jwt.httpModel;

import java.io.Serializable;

public class LoginRes implements Serializable {
  private static final long serialVersionUID = -8091879091924046844L;
  private final String token;

  public LoginRes(String token) {
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }
}
