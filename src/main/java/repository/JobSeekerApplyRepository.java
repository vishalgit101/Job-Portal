package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.JobPostActivity;
import entity.JobSeekerApply;
import entity.JobSeekerProfile;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer>{
	
	List<JobSeekerApply> findByUserId(JobSeekerProfile userId);
	
	List<JobSeekerApply> findByJob(JobPostActivity job);
}
