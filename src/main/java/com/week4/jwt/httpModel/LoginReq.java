package com.week4.jwt.httpModel;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginReq {
  @NotBlank(message = "username cannot be blank")
  private String username;
  @NotBlank(message = "email cannot be blank")
  @Email(message="Invalid email format")
  private String email;
  @NotBlank(message = "password cannot be blank")
  private String password;

}
