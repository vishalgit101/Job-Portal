package controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import entity.RecruiterProfile;
import entity.Users;
import repository.UsersRepository;
import services.RecruiterProfileService;
import util.FileUploadUtil;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {
	private final UsersRepository userRepository;
	private final RecruiterProfileService recruiterProfileService;

	public RecruiterProfileController(UsersRepository userRepository, RecruiterProfileService recruiterProfileService) {
		super();
		this.userRepository = userRepository;
		this.recruiterProfileService = recruiterProfileService;
	}
	
	@GetMapping("/")
	public String recruiterProfile(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users users= userRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find the user"));
			Optional<RecruiterProfile> recruiterProfile = this.recruiterProfileService.getOne(users.getUserId());
			
			if(!recruiterProfile.isEmpty()) {
				model.addAttribute("profile", recruiterProfile.get());
			}
		}
		return "recruiter_profile";
	}
	
	@PostMapping("/addNew")
	public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile multipartFile, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUsername = authentication.getName();
			Users users= userRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find the user"));
			
			recruiterProfile.setUserId(users);
			recruiterProfile.setUserAccountId(users.getUserId());
		}
		model.addAttribute("profile", recruiterProfile);
		String fileName = "";
		if(!multipartFile.getOriginalFilename().equals("")) {
			fileName= StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
			recruiterProfile.setProfilePhoto(fileName);
		}
		RecruiterProfile savedUser = this.recruiterProfileService.addNew(recruiterProfile);
		
		String uploadDir = "photo/recruiter/" + savedUser.getUserAccountId();
		
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/dashboard";
	}
}
