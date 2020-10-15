package com.wellsfargo.fsd.its.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.its.entity.InterviewEntity;


@Repository
public interface Interviewdao extends JpaRepository<InterviewEntity,Integer>{
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewId =:interviewId")
	List<InterviewEntity> findAllById(Integer iId);
	
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewName =:interviewName or i.interviewerName=:interviewerName")
	List<InterviewEntity> findByNameAndInterviewer(String interviewName, String interviewerName);

}
