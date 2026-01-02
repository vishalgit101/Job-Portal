package com.vishal.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.jobportal.entity.JobSeekerProfile;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer> {
}
