package com.hacknitr.wastemanagement.sevice;

import java.util.List;
import java.util.Set;

import com.hacknitr.wastemanagement.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import com.hacknitr.wastemanagement.model.User;
import com.hacknitr.wastemanagement.model.UserRole;



@Service
public interface UserService {
	
	public User createUser(User user,Set<UserRole>userRoles) throws Exception;
    
	public User findUser(String username);
	
	public void deleteUser(Long id);

    List<User> findAllUsers();

	public User fetchUser(Long id);

	public void resetPassword(String email) throws UserNotFoundException;

	public void newPassword(String email, String password) throws UserNotFoundException;
}