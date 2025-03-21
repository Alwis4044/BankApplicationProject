package com.youtube.bank.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	
}
