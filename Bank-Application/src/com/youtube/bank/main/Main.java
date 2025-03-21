package com.youtube.bank.main;

import java.util.Scanner;

import com.youtube.bank.entity.User;
import com.youtube.bank.service.UserService;

// Starting point of our application
public class Main {
	
	// We need to take username and password from the user
	// Hence we are declaring a scanner class
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String args[]) {
		
		UserService userService = new UserService();
		
		// Taking the username and password from the user
		System.out.println("Enter your username: ");
		String username = scanner.next();
		
		System.out.println("Enter your password: ");
		String password = scanner.next();
		
		// Verifying the input taken from user by printing them
		//System.out.println("Username: " + username + "\nPassword: " + password);
		
		User user = userService.login(username, password);
		if(user != null) {
			System.out.println("You are logged in successfully");
		}
		else {
			System.out.println("Login failed"); 
			
		}
		
	}
}
