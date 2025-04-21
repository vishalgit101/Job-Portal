package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.UsersType;
import repository.UsersTypeRepository;

@Service
public class UsersTypeService {
	
	private UsersTypeRepository usersTypeRepository;

	@Autowired
	public UsersTypeService(UsersTypeRepository usersTypeRepository) {
		super();
		this.usersTypeRepository = usersTypeRepository;
	}
	
	public List<UsersType> getAll(){
		return this.usersTypeRepository.findAll();
	}
	
	
}
