package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.JobPostActivity;
import entity.JobSeekerApply;
import entity.JobSeekerProfile;
import repository.JobSeekerApplyRepository;

@Service
public class JobSeekerApplyService {
	private final JobSeekerApplyRepository jobSeekerApplyRepository;

	@Autowired
	public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
		super();
		this.jobSeekerApplyRepository = jobSeekerApplyRepository;
	} 
	
	public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId){
		return this.jobSeekerApplyRepository.findByUserId(userAccountId);
	}
	
	public List<JobSeekerApply> getJobCandidates(JobPostActivity job){
		return this.jobSeekerApplyRepository.findByJob(job);
	}

	public void addNew(JobSeekerApply jobSeekerApply) {
		System.out.println(jobSeekerApply);
		this.jobSeekerApplyRepository.save(jobSeekerApply);
		
	}
	
}
