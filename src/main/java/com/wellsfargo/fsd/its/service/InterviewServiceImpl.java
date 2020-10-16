package com.wellsfargo.fsd.its.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.Interviewdao;
import com.wellsfargo.fsd.its.dao.Userdao;
import com.wellsfargo.fsd.its.entity.InterviewEntity;
import com.wellsfargo.fsd.its.entity.UserEntity;
import com.wellsfargo.fsd.its.exception.InterviewTrackerException;
import com.wellsfargo.fsd.its.model.AttendeeModel;
import com.wellsfargo.fsd.its.model.InterviewModel;
import com.wellsfargo.fsd.its.model.UserModel;

@Service
public class InterviewServiceImpl implements InterviewService {
	
	@Autowired
	private Interviewdao intvwrepo;
	
	@Autowired
	private Userdao userrepo;
	
	private InterviewEntity toInterviewEntity(InterviewModel intvwmodel) {
		if(intvwmodel.getAttendee()==null)
			return new InterviewEntity(intvwmodel.getInterviewId(),intvwmodel.getInterviewName(),intvwmodel.getInterviewerName(),
					intvwmodel.getUserSkills(),intvwmodel.getDateofInterview(),intvwmodel.getIntrvStatus(),intvwmodel.getRemarks());
		else
			return new InterviewEntity(intvwmodel.getInterviewId(),intvwmodel.getInterviewName(),intvwmodel.getInterviewerName(),
					intvwmodel.getUserSkills(),intvwmodel.getDateofInterview(),intvwmodel.getIntrvStatus(),
					intvwmodel.getRemarks(),toUserEntities(intvwmodel.getAttendee()));
			
	}
	
	private InterviewModel toInterviewModel(InterviewEntity entity) {
		if(entity.getAttendees()==null) 
			return new InterviewModel(entity.getInterviewId(),entity.getInterviewName(),entity.getInterviewerName(),
					entity.getUserSkills(),entity.getDateofInterview(),entity.getIntrvStatus(),entity.getRemarks());
			else
				return new InterviewModel(entity.getInterviewId(),entity.getInterviewName(),entity.getInterviewerName(),
						entity.getUserSkills(),entity.getDateofInterview(),entity.getIntrvStatus(),entity.getRemarks(),
						toUserModels(entity.getAttendees()));
		
	}
	
	private Set<UserEntity> toUserEntities(Set<UserModel> userModels){
		Set<UserEntity> entities = null;
		entities = userModels.stream().map(e -> toUserEntity(e)).collect(Collectors.toSet());
		return entities;
		
	}
	
	private UserEntity toUserEntity(UserModel model) {
		return new UserEntity (model.getUserId(),model.getFirstName(),model.getLastName(),model.getEmail(),
				model.getMobile());
	}
	
	private Set<UserModel> toUserModels(Set<UserEntity> userEntities){
		Set<UserModel> models = null;
		models = userEntities.stream().map(e->toUserModel(e)).collect(Collectors.toSet());
		return models;
	}
	
	private UserModel toUserModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFirstName(),entity.getLastName(),
				entity.getEmail(),entity.getMobile());
	}

	@Override
	@Transactional
	public InterviewModel add(InterviewModel interview) throws InterviewTrackerException {
		if(interview!=null) {
			if (intvwrepo.existsById(interview.getInterviewId())) {
				throw new InterviewTrackerException("InterviewId is already in use");
			}
			
			interview = toInterviewModel(intvwrepo.save(toInterviewEntity(interview)));
		}
		return interview;
	}
	
	@Override
	@Transactional
	public String addAttendee(AttendeeModel attendee) throws InterviewTrackerException{
		if(attendee!=null) {
			if(!userrepo.existsById(attendee.getUserId())) {
				throw new InterviewTrackerException("User does not exist");
			}
			
			if (!intvwrepo.existsById(attendee.getInterviewId())) {
				throw new InterviewTrackerException("InterviewId does not exist");
			}
			
			InterviewModel interview = getInterviewById(attendee.getInterviewId());
			for(UserModel user: interview.getAttendee()) {
				if(user.getUserId() == attendee.getUserId()) {
					throw new InterviewTrackerException("User has already attended the interview");
				}
			}
			
			Set <UserModel> users = interview.getAttendee();
			users.add(getUserById(attendee.getUserId()));
			interview.setAttendee(users);
			intvwrepo.save(toInterviewEntity(interview));
			return "User with Id:"+ getUserById(attendee.getUserId())+"is added succesfully to the interview";
		}
		return "Error adding User with Id:" + attendee.getUserId();
	}
	
	public UserModel getUserById(int userId) {
		UserEntity entity = userrepo.findById(userId).orElse(null);
		return entity!=null?toUserModel(entity):null;
	}

	@Override
	public boolean deleteInterview(int interviewId) throws InterviewTrackerException {
		if(!intvwrepo.existsById(interviewId)) {
			throw new InterviewTrackerException("InterviewId does not exist");
		}
		
		intvwrepo.deleteById(interviewId);
		return false;
	}

	@Override
	public InterviewModel updateStatus(Integer interviewId, String Status) throws InterviewTrackerException {
		if(!intvwrepo.existsById(interviewId)) {
			throw new InterviewTrackerException("InterviewId does not exist");
		}
		InterviewModel interview = getInterviewById(interviewId);
		interview.setIntrvStatus(Status);
		intvwrepo.save(toInterviewEntity(interview));
		return getInterviewModel(toInterviewEntity(interview));
	}

	@Override
	public Set<InterviewModel> getinterview(String interviewName, String interviewerName) {
		Set<InterviewEntity> entities = new HashSet<InterviewEntity>(intvwrepo.findByNameAndInterviewer(interviewName, interviewerName));
		Set<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}

	@Override
	public String getInterviewCount() {
		Set<InterviewEntity> entities = new HashSet<InterviewEntity>(intvwrepo.findAll());
		if(entities!=null)
			return "Total no of interviews:"+ entities.size();
		else
			return "No Interviews going on at this time";
	}

	@Override
	public Set<InterviewModel> getAllInterviewDetails() {
		Set<InterviewEntity> entities = new HashSet<InterviewEntity>(intvwrepo.findAll());
		Set<InterviewModel> models = null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> getInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}
	
	private InterviewModel getInterviewModel(InterviewEntity entity) {
		return new InterviewModel(entity.getInterviewId(),entity.getInterviewName(),entity.getInterviewerName(),
				entity.getUserSkills(),entity.getDateofInterview(),entity.getIntrvStatus(),entity.getRemarks());
	}

	@Override
	public Set<UserModel> showUsers(int interviewId) throws InterviewTrackerException {
		if(!intvwrepo.existsById(interviewId)) {
			throw new InterviewTrackerException("InterviewId does not exist");
		}
		return toUserModels(intvwrepo.findById(interviewId).orElse(null).getAttendees());
	}

	

	@Override
	public InterviewModel getInterviewById(int interviewId) {
		InterviewEntity entity = intvwrepo.findById(interviewId).orElse(null);
		return entity!=null?toInterviewModel(entity):null;
	}

}
