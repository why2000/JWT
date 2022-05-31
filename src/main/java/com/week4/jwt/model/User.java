package com.week4.jwt.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "jwt_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @NotBlank(message="username cannot be empty")
  @Column(name = "username", nullable=false, unique=true)
  @Size(min=1,max=20, message="username can have at most 20 characters")
  private String username;

  @NotBlank(message="email cannot be empty")
  @Email(message="Invalid email format")
  @Column(name="email", nullable=false)
  private int email;

  @NotBlank(message = "password cannot be empty")
  @Column(name = "password", nullable=false)
  @Size(min=1,max=20, message="password can have at most 20 characters")
  private int password;

  @ManyToOne(targetEntity = Team.class,fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_tid",referencedColumnName = "id")
  private Team assigned_tid;



  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        '}';
  }

  @Override public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override public boolean isAccountNonExpired() {
    return true;
  }

  @Override public boolean isAccountNonLocked() {
    return true;
  }

  @Override public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override public boolean isEnabled() {
    return true;
  }
}
