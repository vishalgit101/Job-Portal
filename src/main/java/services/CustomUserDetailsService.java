package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.Users;
import repository.UsersRepository;
import util.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	// DI repository...
	private UsersRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UsersRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find the User!"));
		return new CustomUserDetails(user);
	}

}
