package com.wellsfargo.fsd.its.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.its.dao.Interviewdao;
import com.wellsfargo.fsd.its.dao.Userdao;
import com.wellsfargo.fsd.its.entity.UserEntity;
import com.wellsfargo.fsd.its.exception.InterviewTrackerException;
import com.wellsfargo.fsd.its.model.UserModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private Userdao userrepo;
	
	@Autowired
	private Interviewdao intvwrepo;
	
	private UserEntity toEntity(UserModel model) {
		return new UserEntity(model.getUserId(),model.getFirstName(),model.getLastName(),
				model.getEmail(),model.getMobile());
	}
	
	
	private UserModel toModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFirstName(),entity.getLastName(),
				entity.getEmail(),entity.getMobile());
	}

	@Override
	@Transactional
	public UserModel add(UserModel user) throws InterviewTrackerException {
		if(user!=null) {
			if(userrepo.existsById(user.getUserId())) {
				throw new InterviewTrackerException("UserId already Exists");
			}
			user = toModel(userrepo.save(toEntity(user)));
		}
		return user;
	}

	/*@Override
	@Transactional
	public UserModel save(UserModel user) throws InterviewTrackerException {
			if(user!=null) {
				if(!userrepo.existsById(user.getUserId())) {
					throw new InterviewTrackerException("UserId not found");
				}
				
				user = toModel(userrepo.save(toEntity(user)));
			}
		return user;
	}*/

	@Override
	@Transactional
	public boolean deleteuser(int userId) throws InterviewTrackerException {
			if(!userrepo.existsById(userId)) {
				throw new InterviewTrackerException("UserId not found");
			}
			UserEntity userEntity = userrepo.findById(userId).orElse(null);
			userEntity.removeInterviews();
			userrepo.flush();
			userrepo.delete(userEntity);
		return true;
	}

	@Override
	public UserModel getUser(int userId) {
		UserEntity entity = userrepo.findById(userId).orElse(null);
		return entity!=null?toModel(entity):null;
	}

	@Override
	public List<UserModel> getAllUsers() {
		List<UserEntity> entities= userrepo.findAll();
		List<UserModel> models = null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());
			/*models = new ArrayList<>();
			for(UserEntity e:entities) {
				models.add(toModel(e));
			}*/
		
		}
		return models;
	}

}
