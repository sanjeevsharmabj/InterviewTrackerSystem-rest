package com.wellsfargo.fsd.its.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserModel {

	
	@NotNull(message = "UserId is mandatory")
	@Min(value=1,message = "UserId cannot be negative or zero")
	private Integer userId;
	
	@NotNull(message = "First Name is mandatory")
	@Size(min=5, max = 30,message = "First Name should be between 5 to 30 characters in length")
	private String firstName;
	
	@NotNull(message = "Last Name is mandatory")
	@Size(min=5, max=25,message = "Last Name should be between 5 to 25 characters in length")
	private String lastName;
	
	@NotNull(message = "EmailId is mandatory")
	@NotBlank(message = "Email address cannot be blank")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	private String email;
	
	@NotNull(message = "Mobile Number is mandatory")
	@Size(min=10, max=10,message = "Mobile number should be valid and 10 characters in length")
	private Integer mobile;

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(Integer userId, String firstName, String lastName, String email, Integer mobile) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	
	
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobile=" + mobile + "]";
	}
	
	
}
