package com.week4.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jwt_team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @NotBlank(message = "team name cannot be empty")
  @Size(min=1,max=20, message="team name can have at most 20 characters")
  @Column(name = "team_name", nullable = false, unique = true)
  private String team_name;

  @PositiveOrZero(message = "target annual profit must be positive")
  @Column(name="annual_target", nullable = false)
  private int annual_target;

  @Column(name="manager_id", nullable = false)
  private int manager_id;


  @OneToMany(mappedBy = "assigned_tid",cascade = CascadeType.ALL)
  private List<User> userList = new ArrayList<>();


}
