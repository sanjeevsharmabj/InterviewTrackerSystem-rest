package com.wellsfargo.fsd.its.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.its.exception.InterviewTrackerException;
import com.wellsfargo.fsd.its.model.AttendeeModel;
import com.wellsfargo.fsd.its.model.InterviewModel;
import com.wellsfargo.fsd.its.model.UserModel;
import com.wellsfargo.fsd.its.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewController {
	
	@Autowired 
	private InterviewService interviewservice;
	
	@GetMapping("/Showinterviews")
	public ResponseEntity<Set<InterviewModel>> getAllInterviews(){
		return new ResponseEntity<Set<InterviewModel>>(interviewservice.getAllInterviewDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<String> getCountOfInterviews(){
		return new ResponseEntity<>(interviewservice.getInterviewCount(),HttpStatus.OK);
	}
	
	@GetMapping("/showAttendee/{id}")
	public ResponseEntity<Set<UserModel>> showAttendees(@PathVariable("id") int interviewId) throws InterviewTrackerException{
		return new ResponseEntity<>(interviewservice.showUsers(interviewId),HttpStatus.OK);
	}
	
	@GetMapping("/showInterviews/{InterviewName}/{InterviewerName}")
	public ResponseEntity<Set<InterviewModel>> getInterviews(@PathVariable("InterviewName")String interviewName,
			@PathVariable("InterviewerName") String interviewerName){
		ResponseEntity<Set<InterviewModel>> resp=null;
		Set<InterviewModel> interview = interviewservice.getinterview(interviewName, interviewerName);
		if(interview!=null) {
			resp = new ResponseEntity<>(interview,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<InterviewModel> createInterview(@RequestBody @Valid InterviewModel interview,
			BindingResult result) throws InterviewTrackerException {
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewservice.add(interview),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInterview(@PathVariable("id")int interviewId) throws InterviewTrackerException{
		interviewservice.deleteInterview(interviewId);
		return new ResponseEntity<>("InterviewId deleted Successfully",HttpStatus.OK);
	}
	
	@PutMapping("/addAttendee")
	public ResponseEntity<String> addAttendee(@RequestBody @Valid AttendeeModel attendee,
			BindingResult result ) throws InterviewTrackerException{
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		
		return new ResponseEntity<>(interviewservice.addAttendee(attendee),HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{interviewId}/{Status}")
	public ResponseEntity<InterviewModel> updateStatus(@PathVariable("interviewId") int interviewId,
			@PathVariable("Status") String Status) throws InterviewTrackerException{
		return new ResponseEntity<>(interviewservice.updateStatus(interviewId, Status),HttpStatus.OK);
	}
			

}
