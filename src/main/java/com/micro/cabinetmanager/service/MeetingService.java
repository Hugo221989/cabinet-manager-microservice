package com.micro.cabinetmanager.service;

import org.springframework.stereotype.Service;

import com.micro.cabinetmanager.model.MeetingDto;
import com.micro.cabinetmanager.model.MeetingListDto;


public interface MeetingService {
	
	MeetingListDto getAllMeetingsByStudentId(Long studentId);
	MeetingDto findMeetingById(long id);
	MeetingDto saveMeeting(MeetingDto meeting);
	MeetingDto updateMeeting(MeetingDto meeting);
	void deleteStudentMeeting(Long idMeeting);
	void deleteAllStudentMeetings(Long idStudent);
	
}
