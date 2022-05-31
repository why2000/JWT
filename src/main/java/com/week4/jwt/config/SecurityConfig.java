package com.week4.jwt.config;

import com.week4.jwt.security.JwtEntryPoint;
import com.week4.jwt.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String SECRET = "Week4Secret";
  public static final String PREFIX = "Bearer ";
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  @Autowired
  private JwtEntryPoint jwtEntryPoint;

  @Autowired
  private UserDetailsService jwtUserDetailsService;

  @Autowired
  private JwtFilter jwtFilter;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // We don't need CSRF for this example
    httpSecurity.csrf().disable()
        // dont authenticate this particular request
        .authorizeRequests().antMatchers("/authenticate").permitAll().
        // all other requests need to be authenticated
            anyRequest().authenticated().and().
        // make sure we use stateless session; session won't be used to
        // store user's state.
            exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
