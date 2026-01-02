package com.vishal.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.jobportal.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
