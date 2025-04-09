package com.youtube.bank.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.youtube.bank.entity.Transaction;
import com.youtube.bank.entity.User;

// Writing the logic to verify the user credentials
public class UserRepository {
	// Using collections to store multiple user data
	// Using set collection to avoid duplicate data
	// LinkedHashSet takes care of insertion order
	// Hashset doesn't take care of insertion order, 
	// since order is not important currently
	// we are using Hashset

	// The declared set is common collection where all the user data 
	// will be stored, so setting the collection as static
	
	private static Set<User> users = new HashSet<>();	
	
	// Using an arraylist to store the transaction history since same user can perform transactions
	// in a repeated manner
	private static List<Transaction> transactions = new ArrayList<>();
	
	// Using Map, to store values in Key-value pair
	// We will be storing the UserId as key and a boolean data as value
	Map<String, Boolean> chequeBookRequest = new HashMap<>();
	
	// Using some hard coded data since we are not using a database to store user data
	// Storing all these data into a static block in order to run the program
	static {
		User user1 = new User("admin","admin","1234567","admin",0.0);
		User user2 = new User("user2","user2","12345678","user",1000.0);
		User user3 = new User("user3","user3","123456789","user",2000.0);
		User user4 = new User("user4","user4","123456780","user",2000.0);
		
		users.add(user1);
		users.add(user2); 
		users.add(user3);
		users.add(user4);
	}
	
	// This feature is available for the customer
	public void raiseChequeBookRequest(String userId) {
		chequeBookRequest.put(userId, false);
	}
	
	// A method to print the status of cheque book requests
	public Map<String, Boolean> getAllChequeBookRequest() {
		return chequeBookRequest;
	}
	// A method to perform transactions within customers accounts
	public boolean transferAmount(String userId, String payeeUserId, Double amount) {
		
		// If debit is successful returns true, else false
		boolean isDebit = debit(userId, amount,payeeUserId);
		// If credit is successful returns true, else false
		boolean isCredit = credit(payeeUserId, amount,userId);	
		
		// Returning a boolean value for both actions combined result
		return isDebit && isCredit;
	}
	
	// A method to perform debit transaction
	private boolean debit(String userId, Double amount,String payeeUserId) {
		User user = getUser(userId);
		Double accountBalance = user.getAccountBalance();
		
		//Removing the current user from the list since sets are immutable
		users.remove(user);
		
		// Storing the balance after amount debited from customer's account
		Double finalBalance = accountBalance - amount;
		user.setAccountBalance(finalBalance);
		
		// Updating the debit transaction history
		Transaction transaction = new Transaction(
				LocalDate.now(),
				payeeUserId,
				amount,
				"Debit",
				accountBalance,
				finalBalance,
				userId
		);
		System.out.println(transaction);
		// Adding debit transaction history in the arraylist
		transactions.add(transaction);
		
		// Adding the user with updated bank balance in the set
		return users.add(user);
	}
	
	// A method to perform credit transaction
	private boolean credit(String payeeUserId, Double amount,String userId) {
		User user = getUser(payeeUserId);
		Double accountBalance = user.getAccountBalance();
		
		//Removing the current user from the list since sets are immutable
		users.remove(user);
		
		// Storing the balance after amount credited to customer's account
		Double finalBalance = accountBalance + amount;
		user.setAccountBalance(finalBalance);
		
		// Updating the credit transaction history
		Transaction transaction = new Transaction(
				LocalDate.now(),
				userId,
				amount,
				"Credit",
				accountBalance,
				finalBalance,
				payeeUserId
		);
		System.out.println(transaction);
		// Adding the credit transaction history in the arraylist
		transactions.add(transaction);
		
		// Adding the user with updated bank balance in the set
		return users.add(user);
	}
	
	
	// A method to print the transaction history of specific customer
	public void printTransactions(String userId) {
		// Finding the transaction data of user and storing it in a list
		List<Transaction> filteredTransactions = transactions.stream().filter(transaction -> transaction.getTransactionPerformedBy().equals(userId)).collect(Collectors.toList());
		
		// Retrieving data from the list filteredTransactions and printing the transaction history of the user
		System.out.println("Date\tUser Id\tAmount\tType\tInitial Balance\tFinal Balance");
		System.out.println("------------------------------------------------------------------------");
		for(Transaction t:filteredTransactions) {
			System.out.println(t.getTransactionDate()
					+ "\t" + t.getTransactionUserId()
					+ "\t" + t.getTransactionAmount()
					+ "\t" + t.getTransactionType()
					+ "\t" + t.getInitialBalance()
					+ "\t" + t.getFinalBalance()
					); 
		}
		System.out.println("------------------------------------------------------------------------");

		
	}
	
	// A method to get the userId
	// Using stream API to fetch the userId from the data stored in the list
	public User getUser(String userId) {
		List<User> result = users.stream().filter(user -> user.getUsername().equals(userId)).collect(Collectors.toList());
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;

	}
	
	// Using stream API to fetch the user id to retrieve the bank balance
	public Double checkBankBalance(String userId) {
		List<User> result = users.stream().filter(user -> user.getUsername().equals(userId)).collect(Collectors.toList());
	
		if(!result.isEmpty()) {
			return result.get(0).getAccountBalance();
		}
		else {
			return null;
		}
	}
	
	public void printUsers() {
		System.out.println(users);
	}
	
	// Two approaches to check the login
	// 1. Use for loop or for each loop and check conditions
	// 2. Use Stream APIs
	public User login(String username, String password) {
		// Using Stream API
		// filter function uses a lambda expression (predicate)
		List<User> finalList = users.stream().filter(user -> user.getUsername().equals(username)&& user.getPassword().equals(password)).collect(Collectors.toList());
		
		if(!finalList.isEmpty()) {
			return finalList.get(0);
		}
		else {
			return null;
		}
		
	}
	
	// Creating a function to add new customer
	public boolean addNewCustomer(String username,String password, String contact) {
		User user = new User(username,password,contact,"user",500.0);
		return users.add(user);
	}
	
}
