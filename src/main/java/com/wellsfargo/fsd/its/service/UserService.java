package com.wellsfargo.fsd.its.service;

import java.util.List;

import com.wellsfargo.fsd.its.model.UserModel;
import com.wellsfargofsd.its.exception.InterviewTrackerException;

public interface UserService {
	
	UserModel add(UserModel user) throws InterviewTrackerException;
	UserModel save(UserModel user) throws InterviewTrackerException;
	
	boolean deleteuser(int userId) throws InterviewTrackerException;
	
	UserModel getUser(int userId);
	List<UserModel> getAllUsers();
	

}
