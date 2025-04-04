package com.youtube.bank.main;

import java.util.Scanner;

import com.youtube.bank.entity.User;
import com.youtube.bank.service.UserService;

// Starting point of our application
public class Main {
	
	// We need to take username and password from the user
	// Hence we are declaring a scanner class
	private static Scanner scanner = new Scanner(System.in);
	
	//Shifting main object to global scope (static) for flexible access
	static Main main = new Main();
	
	//Shifting userService object to global scope (static)
	static UserService userService = new UserService();
	
	public static void main(String args[]) {
		
		// Creating an object of main class to call the functions
		// since main is static, therefore static cannot call non-static
		//Main main = new Main();
		
		
		
		// Using while loop to run the application unless user wants to 
		// close it manually
		while(true) {
			// Taking the username and password from the user
			System.out.println("Enter your username: ");
			String username = scanner.next();
			
			System.out.println("Enter your password: ");
			String password = scanner.next();
			
			// Verifying the input taken from user by printing them
			//System.out.println("Username: " + username + "\nPassword: " + password);
			
			// userService.printUsers();
			
			User user = userService.login(username, password);
			if(user != null && user.getRole().equals("admin")) {
				//System.out.println("You are logged in successfully");
				main.initAdmin();
				//Create customers option will be available only for admin
			} 
			else if(user != null && user.getRole().equals("user")) {
				main.initCustomer(user);	// Passing user object to fetch user id
			}
			else {
				System.out.println("Login failed"); 
			}
			
		}
		
	}
		
		
	private void initAdmin() {
		// Dummy message
		//System.out.println("You are an admin");
		
		boolean flag = true;
		
		// Using a while loop to keep the admin logged in unless exit option
		// is chosen
		while(flag) {
			// Creating a menu to display options for admin
			System.out.println("1. Exit/Logout");
			System.out.println("2. Create a customer account");
			
			// Taking input from the admin
			int selectOption = scanner.nextInt();
			
			switch(selectOption) {
				case 1:
					//System.out.println("Write some logic for exit");
					flag = false;
					System.out.println("You have successfully logged out...");
					break;
				case 2:
					//System.out.println("Add a new customer");
					main.addNewCustomer();
					break;
				default:
					System.out.println("Wrong choice!!");
			}
		}
	
	}
	
	// This method is only available for the admin
	private void addNewCustomer() {
		// Setting credentials for new customer
		System.out.println("Enter username");
		String username = scanner.next();
		
		System.out.println("Enter password");
		String password = scanner.next();
		
		System.out.println("Enter contact number");
		String contact = scanner.next();
		
		// A boolean value is returned and stored in result
		boolean result = userService.addNewCustomer(username, password, contact);
		
		if(result) {
			System.out.println("Customer account is created...");
		}
		else {
			System.out.println("Customer account creation failed...");
		}
	}
	
	// This method should be accessible by the customer
	// Adding an argument to fetch user id
	private void initCustomer(User user) {
		// Dummy message
		//System.out.println("You are a customer");
		
		boolean flag = true;
		
		while(flag) {
			System.out.println("1. Exit/Logout");
			System.out.println("2. Check bank balance");
			System.out.println("3. Fund Transfer");
			int selectedOption = scanner.nextInt();
			
			switch(selectedOption) {
			case 1:
				//System.out.println("Write some logic for exit");
				flag = false;
				System.out.println("You have successfully logged out...");
				break;
			case 2:
				// calling the function using static main object
				Double balance = main.checkBankBalance(user.getUsername());
				// Printing the bank balance
				if(balance != null) {
					System.out.println("Your bank balance is " + balance);
				} else {
					System.out.println("Check your username");
				}
				break;
			case 3:
				main.fundTransfer(user);
				break;
			
			default:
				System.out.println("Wrong choice!!");
			}	
		}
		
	}
	
	// Function available for the customer
	private void fundTransfer(User userDetails) {
		System.out.println("Enter payee account user id");
		String payeeAccountId = scanner.next();
		
		// Verifying the user id of payee account
		User user = getUser(payeeAccountId);
		
		if(user != null) {
			
			//Printing a dummy statement to test the function
			//System.out.println("Valid username");
			
			System.out.println("Enter amount to transfer");
			Double amount = scanner.nextDouble();
			
			// We need to check whether the amount is less than customer's current bank balance
			Double userAccountBalance = checkBankBalance(userDetails.getUsername());
			
			if(userAccountBalance >= amount) {
				boolean result = userService.transferAmount(userDetails.getUsername(), payeeAccountId, amount);
				
				// Result contains the status of transferAmount method
				// If true, transfer successful, else transfer failed
				if(result) {
					System.out.println("Amount transferred successfully...");
				} else {
					System.out.println("Transfer failed...");
				}
				
			} else {
				System.out.println("Your balance is insufficient: " + userAccountBalance);
			}
			
		}else {
			System.out.println("Please enter a valid username...");
		}
	}
	
	private User getUser(String userId) {
		return userService.getUser(userId);
	}
	
	
	// This function is available for the user
	private Double checkBankBalance(String userId) {
		return userService.checkBankBalance(userId);
	}
}
