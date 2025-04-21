	package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import entity.Users;
import entity.UsersType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import services.JobSeekerApplyService;
import services.JobSeekerSaveService;
import services.UsersService;
import services.UsersTypeService;

@Controller
public class UsersController {
	
	private final UsersTypeService usersTypeService;
	private final UsersService usersService;
	
	

	@Autowired
	public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
		super();
		this.usersTypeService = usersTypeService;
		this.usersService =  usersService;
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		List<UsersType> usersTypes = this.usersTypeService.getAll();
		model.addAttribute("getAllTypes", usersTypes);
		model.addAttribute("user", new Users());
		return "register";
	}
	
	@PostMapping("/register/new")
	public String userRegistration(@Valid Users users) {
		System.out.println("User: " + users);
		this.usersService.addNew(users);
		//model.addAttribute("user", users);
		return "redirect:/dashboard/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/";
	}
	
}
