package com.week4.jwt.service;

import com.week4.jwt.model.Team;


public interface TeamService {
  boolean updateTeam(Team team, String uid);
  Team findTeamById(String tid, String uid);
}
