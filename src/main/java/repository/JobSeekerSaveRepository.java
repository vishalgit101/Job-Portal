package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.JobPostActivity;
import entity.JobSeekerProfile;
import entity.JobSeekerSave;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {
	
	List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
	
	
	List<JobSeekerSave> findByJob(JobPostActivity job);
}
