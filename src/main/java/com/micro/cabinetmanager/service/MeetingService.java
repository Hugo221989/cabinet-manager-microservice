package com.micro.cabinetmanager.service;

import com.micro.cabinetmanager.model.MeetingDto;
import com.micro.cabinetmanager.model.MeetingListDto;


public interface MeetingService {
	
	MeetingListDto getAllMeetings();
	MeetingListDto getAllMeetingsByStudentId(Long studentId);
	MeetingDto findMeetingById(long id);
	MeetingDto saveMeeting(MeetingDto meeting);
	MeetingDto updateMeeting(MeetingDto meeting);
	void deleteStudentMeeting(Long idMeeting);
	void deleteAllStudentMeetings(Long idStudent);
	
}
