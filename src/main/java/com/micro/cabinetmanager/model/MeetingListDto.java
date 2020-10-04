package com.micro.cabinetmanager.model;

import java.util.List;

public class MeetingListDto {

	List<MeetingDto> meetingList;

	
	public MeetingListDto() {

	}

	public MeetingListDto(List<MeetingDto> meetingList) {
		super();
		this.meetingList = meetingList;
	}

	public List<MeetingDto> getMeetingList() {
		return meetingList;
	}

	public void setMeetingList(List<MeetingDto> meetingList) {
		this.meetingList = meetingList;
	}
	
}
