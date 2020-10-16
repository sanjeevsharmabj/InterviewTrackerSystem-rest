package com.wellsfargo.fsd.its.model;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InterviewModel {
	
	@NotNull(message="Interview Id is mandatory")
	@Min(value=1, message ="Interview Id cannot be negative or zero")
	private Integer interviewId;
	@NotNull(message="Interview Name is mandatory")
	@Size(min=3,max=30,message = "Interview Name is expected to be 3 to 30 chars in length")
	private String interviewName;
	@NotNull(message="Interviewer Name is mandatory")
	@Size(min=5,max=30,message = "Interviewer Name is expected to be 5 to 30 chars in length")
	private String interviewerName;
	@NotNull(message="Users Skill is mandatory")
	@Size(min=5,max=30,message = "Users Skill is expected to be 5 to 30 chars in length")
	private String userSkills;
	private LocalDate dateofInterview;
	@NotNull(message="Interview Status is mandatory")
	@Size(min=5,max=100,message = "Interview status is expected to be 5 to 100 chars in length")
	private String intrvStatus;
	@NotNull(message="Remarks is mandatory")
	@Size(min=5,max=100,message = "Remarks is expected to be 5 to 100 chars in length")
	private String remarks;
	
	@Valid
	private Set<UserModel> attendee;	
	
	public Set<UserModel> getAttendee() {
		return attendee;
	}
	
	public void setAttendee(Set<UserModel> attendees) {
		this.attendee = attendees;
	}
	
	public InterviewModel() {
		super();
		// left unimplemented
	}

	public InterviewModel(Integer interviewId, String interviewName, String interviewerName, String userSkills,
			LocalDate dateofInterview, String intrvStatus, String remarks) {
		super();
		this.interviewId = interviewId;
		this.interviewName = interviewName;
		this.interviewerName = interviewerName;
		this.userSkills = userSkills;
		this.dateofInterview = dateofInterview;
		this.intrvStatus = intrvStatus;
		this.remarks = remarks;
	}
	
	public InterviewModel(Integer interviewId, String interviewName, String interviewerName, String userSkills,
			LocalDate dateofInterview, String intrvStatus, String remarks,Set<UserModel> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewName = interviewName;
		this.interviewerName = interviewerName;
		this.userSkills = userSkills;
		this.dateofInterview = dateofInterview;
		this.intrvStatus = intrvStatus;
		this.remarks = remarks;
		this.attendee = attendees;
	}
	

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewName() {
		return interviewName;	
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	public LocalDate getDateofInterview() {
		return dateofInterview;
	}

	public void setDateofInterview(LocalDate dateofInterview) {
		this.dateofInterview = dateofInterview;
	}

	public String getIntrvStatus() {
		return intrvStatus;
	}

	public void setIntrvStatus(String intrvStatus) {
		this.intrvStatus = intrvStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Interview Details [interviewId=" + interviewId + ", interviewName=" + interviewName + ", interviewerName="
				+ interviewerName + ", userSkills=" + userSkills + ", dateofInterview=" + dateofInterview
				+ ", intrvStatus=" + intrvStatus + ", remarks=" + remarks + "]";
	}
	
	
}
