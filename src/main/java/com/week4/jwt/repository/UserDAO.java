package com.week4.jwt.repository;

import com.week4.jwt.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
  User findByUsername(String username);
  User getById(Long id);

  Optional<User> findById(Long id);
}
