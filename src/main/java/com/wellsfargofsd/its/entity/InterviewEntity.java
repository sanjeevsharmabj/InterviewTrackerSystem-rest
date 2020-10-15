package com.wellsfargofsd.its.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@SuppressWarnings("serial")
@Entity
@Table(name="Interview")
public class InterviewEntity implements Serializable,Comparable<InterviewEntity>{
	
	@Id
	@Column(name="interviewId")
	private Integer interviewId;
	
	@Column(name="interviewName")
	private String interviewName;
	
	@Column(name="interviewerName")
	private String interviewerName;
	
	@Column(name="uSkills")
	private String userSkills;
	
	@Column(name="datentimeofI")
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDate dateofInterview;
	
	@Column(name="iStatus")
	private String intrvStatus;
	
	@Column(name="remarks")
	private String remarks;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name = "InterviewSchedule", 
	joinColumns = @JoinColumn(name = "interviewId", referencedColumnName = "interviewId"), 
    inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
	private Set<UserEntity> attendees=new HashSet<>();	
	

	public InterviewEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InterviewEntity(Integer interviewId, String interviewName, String interviewerName, String userSkills,
			LocalDate dateofInterview, String intrvStatus, String remarks,Set<UserEntity> attendees) {
		super();
		this.interviewId = interviewId;
		this.interviewName = interviewName;
		this.interviewerName = interviewerName;
		this.userSkills = userSkills;
		this.dateofInterview = dateofInterview;
		this.intrvStatus = intrvStatus;
		this.remarks = remarks;
		this.attendees =attendees;
	}
	
	
	
	public InterviewEntity(Integer interviewId, String interviewName, String interviewerName, String userSkills,
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
	
	public Set<UserEntity> getAttendees() {
		return attendees;
	}

	public void setAttendees(Set<UserEntity> attendees) {
		this.attendees = attendees;
	}

	@Override
	public String toString() {
		return "InterviewEntity [interviewId=" + interviewId + ", interviewName=" + interviewName + ", interviewerName="
				+ interviewerName + ", userSkills=" + userSkills + ", dateofInterview=" + dateofInterview
				+ ", intrvStatus=" + intrvStatus + ", remarks=" + remarks + "]";
	}
	
	@Override
	public int compareTo(InterviewEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
