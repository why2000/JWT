package com.week4.jwt.service;

import com.week4.jwt.model.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{
  @Override public boolean updateTeam(Team team, String uid) {
    return false;
  }

  @Override public Team findTeamById(String tid, String uid) {
    return null;
  }
}
