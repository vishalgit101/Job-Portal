package services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.RecruiterProfile;
import entity.Users;
import repository.RecruiterProfileRepository;
import repository.UsersRepository;

@Service
public class RecruiterProfileService {
	private final RecruiterProfileRepository recruiterProfileRepository;
	private final UsersRepository usersRepository;
	@Autowired
	public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UsersRepository usersRepository) {
		super();
		this.recruiterProfileRepository = recruiterProfileRepository;
		this.usersRepository = usersRepository;
	}
	
	public Optional<RecruiterProfile> getOne(Integer id){
		return recruiterProfileRepository.findById(id);
	}

	public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
		
		return this.recruiterProfileRepository.save(recruiterProfile);
	}

	public RecruiterProfile getCurrentRecruiterProfile() {
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users user =usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
			
			Optional<RecruiterProfile> recruiterProfile = getOne(user.getUserId());
			return recruiterProfile.orElse(null);
			
		}
		else return null;
	}
}
