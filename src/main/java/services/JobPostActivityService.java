package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.IRecruiterJobs;
import entity.JobCompany;
import entity.JobLocation;
import entity.JobPostActivity;
import entity.RecruiterJobsDto;
import repository.JobPostActivityRepository;

@Service
public class JobPostActivityService {
	
	private final JobPostActivityRepository jobPostActivityRepository;

	@Autowired
	public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
		super();
		this.jobPostActivityRepository = jobPostActivityRepository;
	}
	
	public JobPostActivity addNew(JobPostActivity jobPostActivity) {
		return this.jobPostActivityRepository.save(jobPostActivity);
	}
	
	public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
		
		List<IRecruiterJobs> recruiterJobsDto =  this.jobPostActivityRepository.getRecruiterJobs(recruiter);
		
		List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
		
		for(IRecruiterJobs rec: recruiterJobsDto) {
			JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
			
			JobCompany comp =  new JobCompany(rec.getCompanyId(), rec.getName(), "");
			
			recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
			
			
		}
		
		return recruiterJobsDtoList;
	}

	public List<JobPostActivity> getAll() {
		return this.jobPostActivityRepository.findAll();
		
	}

	public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote,
			LocalDate searchDate) {
		return Objects.isNull(searchDate)? this.jobPostActivityRepository.searchWithoutDate(job,location,remote,type) :
			this.jobPostActivityRepository.search(job, location, remote, type, searchDate);
	}

	public JobPostActivity getOne(int id) {
		return this.jobPostActivityRepository.getOne(id);
	}
}
