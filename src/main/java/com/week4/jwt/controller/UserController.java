package com.week4.jwt.controller;


import com.week4.jwt.httpModel.LoginReq;
import com.week4.jwt.httpModel.LoginRes;
import com.week4.jwt.security.JwtUtils;
import com.week4.jwt.service.UserService;
import com.week4.jwt.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static com.week4.jwt.config.SecurityConfig.PREFIX;


@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private LoginValidator loginValidator;

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginRequest,
      BindingResult bindingResult) throws Exception {
    if(bindingResult.hasErrors()){
      List<FieldError> fieldErrors = bindingResult.getFieldErrors();
      return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);

    }
    authenticate(loginRequest.getUsername(), loginRequest.getPassword());

    final UserDetails userDetails = userService
        .loadUserByUsername(loginRequest.getUsername());

    final String token = jwtUtils.generateToken(userDetails);

    return ResponseEntity.ok(new LoginRes(token));
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }

}

