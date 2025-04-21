package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.RecruiterProfile;

@Repository
public interface RecruiterProfileRepository extends JpaRepository< RecruiterProfile, Integer> {
	 
}
