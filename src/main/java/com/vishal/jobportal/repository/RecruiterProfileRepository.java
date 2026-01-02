package com.vishal.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.jobportal.entity.RecruiterProfile;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Integer> {
}
