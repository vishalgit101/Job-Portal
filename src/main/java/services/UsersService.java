package services;

import java.sql.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import entity.JobSeekerProfile;
import entity.RecruiterProfile;
import entity.Users;
import repository.JobSeekerProfileRepository;
import repository.RecruiterProfileRepository;
import repository.UsersRepository;

@Service
public class UsersService {
	
	private UsersRepository usersRepository;
	private final JobSeekerProfileRepository jobSeekerProfileRepository;
	private final RecruiterProfileRepository recruiterProfileRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository, PasswordEncoder passwordEncoder) {
		super();
		this.usersRepository = usersRepository; 
		this.jobSeekerProfileRepository = jobSeekerProfileRepository;
		this.recruiterProfileRepository = recruiterProfileRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Users addNew(Users users) {
		users.setActive(true);
		users.setRegistrationDate(new Date(System.currentTimeMillis()));
		users.setPassword(this.passwordEncoder.encode(users.getPassword()));
		Users savedUser = usersRepository.save(users);
		System.out.println("Username: " + users.getEmail());
		int userTypeId = users.getUserTypeId().getUserTypeId();
		
		if(userTypeId == 1) {
			this.recruiterProfileRepository.save(new RecruiterProfile(savedUser));
		}else {
			this.jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
		}
		System.out.println("User with Profile: " + savedUser);
		return savedUser;
	}

	public Object getCurrentUserProfile() {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Users users = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could Not find Username"));
			int userId = users.getUserId();
			
			if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
				RecruiterProfile recruiterProfile =recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
				return recruiterProfile;
			}else {
				JobSeekerProfile jobSeekerProfile= jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
				return jobSeekerProfile;
			}
		}
		return null;
	}

	public Users getCurrentUser() {
		org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could Not find Username"));
			//int userId = users.getUserId();
			return user;
		}
		
		return null;
	}

	public Users findByEmail(String currentUsername) {
		return usersRepository.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not find user"));
	}
	
}
