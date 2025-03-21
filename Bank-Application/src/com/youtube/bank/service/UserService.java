package com.youtube.bank.service;

import com.youtube.bank.entity.User;
import com.youtube.bank.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository = new UserRepository();
	
	// Creating a print method to print the user data from user repository file
	public void printUsers() {
		userRepository.printUsers();
	} // The above method will be called in the Main method
	
	public User login(String username, String password) {
		return userRepository.login(username, password);
	}
	
}
