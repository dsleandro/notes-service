package com.leandro.notesservice.repository;

import com.leandro.notesservice.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);

}