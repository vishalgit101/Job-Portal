package services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.JobSeekerProfile;
import entity.Users;
import repository.JobSeekerProfileRepository;
import repository.UsersRepository;

@Service
public class JobSeekerProfileService {
	
	//DI
	private final JobSeekerProfileRepository jobSeekerProfileRepository;
	private final UsersRepository usersRepository;
	
	@Autowired
	public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UsersRepository usersRepository) {
		super();
		this.jobSeekerProfileRepository = jobSeekerProfileRepository;
		this.usersRepository  = usersRepository;
	}



	public Optional<JobSeekerProfile> getOne(Integer id){
		return this.jobSeekerProfileRepository.findById(id);
	}



	public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
		
		return this.jobSeekerProfileRepository.save(jobSeekerProfile);
	}



	public JobSeekerProfile getCurrentSeekerProfile() {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)){
			String currentUsername = authentication.getName();
			Users user = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			
		Optional<JobSeekerProfile> seekerProfile = getOne(user.getUserId());
		
		return seekerProfile.orElse(null);
		}else return null;
	}
}
