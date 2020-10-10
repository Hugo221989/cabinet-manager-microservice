package com.micro.cabinetmanager.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.cabinetmanager.client.UserMeetingClient;
import com.micro.cabinetmanager.model.MeetingDto;
import com.micro.cabinetmanager.model.MeetingListDto;
import com.micro.cabinetmanager.service.MeetingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	//private static final String URL_INFO_SERVICE = "http://192.168.99.101:8082/meeting/";
	//private static final String URL_MEETING_SERVICE = "http://localhost:8082/meeting /";
//	@Autowired 
//	private RestTemplate restTemplate;
	
	@Autowired
	private UserMeetingClient userMeetingClient;
	
	@Override
	@HystrixCommand(fallbackMethod="getAllMeetingsFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public MeetingListDto getAllMeetings() {
		//MeetingListDto meetingList = restTemplate.getForObject(URL_MEETING_SERVICE+"/getAllMeetings/", MeetingListDto.class);
		MeetingListDto meetingList = this.userMeetingClient.getAllMeetings();
		return meetingList;
	}
	
	public MeetingListDto getAllMeetingsFallback() {
		List<MeetingDto> meetingList = new ArrayList<MeetingDto>();
		MeetingListDto meetingListDto = new MeetingListDto(meetingList);
		return meetingListDto;
	}


	@Override
	@HystrixCommand(fallbackMethod="getAllMeetingsByStudentIdFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public MeetingListDto getAllMeetingsByStudentId(Long studentId) {
		//MeetingListDto meetingList = restTemplate.getForObject(URL_MEETING_SERVICE+"getAllMeetingsByStudentId/?studentId="+studentId, MeetingListDto.class);
		MeetingListDto meetingList = this.userMeetingClient.getAllMeetingsByStudentId(studentId);
		return meetingList;
	}
	
	public MeetingListDto getAllMeetingsByStudentIdFallback(Long studentId) {
		MeetingListDto meetingListDto = new MeetingListDto();
		return meetingListDto;
	}

	
	@Override
	@HystrixCommand(fallbackMethod="findMeetingByIdFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public MeetingDto findMeetingById(long id) {
		//MeetingDto meeting = restTemplate.getForObject(URL_MEETING_SERVICE+"?id="+id, MeetingDto.class);
		ResponseEntity<MeetingDto> meeting = this.userMeetingClient.getMeetingData(id);
		return meeting.getBody();
	}
	public MeetingDto findMeetingByIdFallback(long id) {
		MeetingDto meeting = new MeetingDto(1L, "No available", new Date(), new Date(), "No available", "No available", "No available", "No available", true, 1L, "No available");
		return meeting;
	}

	
	@Override
	@HystrixCommand(fallbackMethod="saveMeetingFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public MeetingDto saveMeeting(MeetingDto meeting) {
		//MeetingDto meetingNew = restTemplate.postForObject(URL_MEETING_SERVICE+"", meeting, MeetingDto.class);
		ResponseEntity<MeetingDto> meetingNew = this.userMeetingClient.createStudentMeeting(meeting);
		return meetingNew.getBody();
	}
	
	public MeetingDto saveMeetingFallback(MeetingDto meeting) {
		MeetingDto meetingNew = new MeetingDto(1L, "No available", new Date(), new Date(), "No available", "No available", "No available", "No available", true, 1L, "No available");
		return meetingNew;
	}


	@Override
	@HystrixCommand(fallbackMethod="updateMeetingFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public MeetingDto updateMeeting(MeetingDto meeting) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MeetingDto> requestUpdate = new HttpEntity<>(meeting, headers);
		//ResponseEntity<MeetingDto> meetingNew = restTemplate.exchange(URL_MEETING_SERVICE+"", HttpMethod.PUT,  requestUpdate, MeetingDto.class);
		ResponseEntity<MeetingDto> meetingNew = this.userMeetingClient.updateStudentMeeting(meeting.getId(), meeting);
		return meetingNew.getBody();
	}
	public MeetingDto updateMeetingFallback(MeetingDto meeting) {
		MeetingDto meetingNew = new MeetingDto(1L, "No available", new Date(), new Date(), "No available", "No available", "No available", "No available", true, 1L, "No available");
		return meetingNew;
	}
	

	@Override
	public void deleteStudentMeeting(Long idMeeting) {
		//restTemplate.delete(URL_MEETING_SERVICE+"?idMeeting="+idMeeting);
		this.userMeetingClient.deleteStudentMeeting(idMeeting);

	}

	@Override
	public void deleteAllStudentMeetings(Long idStudent) {
		//restTemplate.delete(URL_MEETING_SERVICE+"deleteAllStudentMeetings/?idStudent="+idStudent);
		this.userMeetingClient.deleteAllStudentMeetings(idStudent);
	}

}
