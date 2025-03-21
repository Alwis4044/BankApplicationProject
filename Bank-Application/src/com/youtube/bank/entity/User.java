package com.youtube.bank.entity;

import java.util.Objects;

public class User {
	
	private String username;
	private String password;
	private String contactNumber;
	private String role;
	private Double accountBalance;
	
	
	public User(String username, String password, String contactNumber, String role, Double accountBalance) {
		super();
		this.username = username;
		this.password = password;
		this.contactNumber = contactNumber;
		this.role = role;
		this.accountBalance = accountBalance;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", contactNumber=" + contactNumber + ", role="
				+ role + ", accountBalance=" + accountBalance + "]";
	}
	// toString() returns the string representation of our object
	// if this method is not defined then on calling the print method, the object reference gets printed

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, password, username);
	}

	// The declared set can still take duplicate values as the set treats each object as different one
	// Creating equals() to avoid insertion of duplicate values inside the set
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
	
	
	
	
	
	
	
	
	
}
