package com.wellsfargo.fsd.its.service;

import java.util.Set;

import com.wellsfargo.fsd.its.exception.InterviewTrackerException;
import com.wellsfargo.fsd.its.model.AttendeeModel;
import com.wellsfargo.fsd.its.model.InterviewModel;
import com.wellsfargo.fsd.its.model.UserModel;

public interface InterviewService {
	
	InterviewModel add(InterviewModel interview) throws InterviewTrackerException;
	boolean deleteInterview(int interviewId) throws InterviewTrackerException;
	InterviewModel updateStatus(Integer interviewId,String Status) throws InterviewTrackerException;
	Set<InterviewModel> getinterview(String interviewName, String interviewerName);
	
	String getInterviewCount();
	Set<InterviewModel> getAllInterviewDetails();
	Set<UserModel> showUsers(int interviewId) throws InterviewTrackerException;
	String addAttendee(AttendeeModel attendee) throws InterviewTrackerException;
	InterviewModel getInterviewById(int interviewId);
	

}
