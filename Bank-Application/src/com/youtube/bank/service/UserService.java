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
	
	// This function's data will be retrieved from UserRepository class file
	public boolean addNewCustomer(String username,String password, String contact) {
		return userRepository.addNewCustomer(username, password, contact);
	}
	
	// This function's data will be retrieved from UserRepository class file
	public Double checkBankBalance(String userId) {
		return userRepository.checkBankBalance(userId);
	}
	
	public User getUser(String userId) {
		return userRepository.getUser(userId);
	}
	
	public boolean transferAmount(String userId, String payeeUserId, Double amount) {
		return userRepository.transferAmount(userId, payeeUserId, amount);
	}
	
	
}
