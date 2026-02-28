package com.learnfirst.userservice.repository;

import com.learnfirst.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServiceRepository extends JpaRepository<User, Integer> {
     User findByEmail(String email);
}
