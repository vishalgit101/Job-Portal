package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.JobPostActivity;
import entity.JobSeekerProfile;
import entity.JobSeekerSave;
import repository.JobSeekerSaveRepository;

@Service
public class JobSeekerSaveService {
	
	// DI
	private final JobSeekerSaveRepository jobSeekerSaveRepository;

	@Autowired
	public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
		super();
		this.jobSeekerSaveRepository = jobSeekerSaveRepository;
	}
	
	public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId){
		return this.jobSeekerSaveRepository.findByUserId(userAccountId);
	}
	
	public List<JobSeekerSave> getCandidates(JobPostActivity job){
		return this.jobSeekerSaveRepository.findByJob(job);
	}

	public void addNew(JobSeekerSave jobSeekerSave) {
		this.jobSeekerSaveRepository.save(jobSeekerSave);
	}
}
