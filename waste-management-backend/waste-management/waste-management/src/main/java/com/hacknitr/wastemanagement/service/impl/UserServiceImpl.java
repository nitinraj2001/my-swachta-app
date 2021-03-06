package com.hacknitr.wastemanagement.service.impl;

import java.util.List;
import java.util.Set;

import com.hacknitr.wastemanagement.repository.RoleRepository;
import com.hacknitr.wastemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.model.UserRole;
import com.hacknitr.wastemanagement.sevice.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User theUser=this.userRepository.findByUsername(user.getUsername());
		
		if(theUser!=null) {
			System.out.println("This username is already exit");
			throw new Exception("this username already exist try again");
		}
		
		else {
			for(UserRole userrole:userRoles) {
				this.roleRepository.save(userrole.getRole());
			}
			user.getUserRole().addAll(userRoles);
			theUser=this.userRepository.save(user);
		}
		return theUser;
	}

    public User findUser(String username) {
		
		return this.userRepository.findByUsername(username);
	}

	public void deleteUser(Long id) {
		
		 this.userRepository.deleteById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User fetchUser(Long id) {
		return this.userRepository.findById(id).get();
	}


}