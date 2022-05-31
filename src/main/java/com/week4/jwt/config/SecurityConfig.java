package com.week4.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String SECRET = "Week4Secret";
  public static final String PREFIX = "Week4JWT ";
  // 10 min
  public static final long EXPIRATION = 600_0000;

  @Override
  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
