package com.micro.cabinetmanager.serviceImpl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.micro.cabinetmanager.model.MeetingDto;
import com.micro.cabinetmanager.model.MeetingListDto;
import com.micro.cabinetmanager.service.MeetingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired 
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod="getAllMeetingsByStudentIdFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public MeetingListDto getAllMeetingsByStudentId(Long studentId) {
		MeetingListDto meetingList = restTemplate.getForObject("http://user-meeting/meeting/getAllMeetingsByStudentId/?studentId="+studentId, MeetingListDto.class);
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
		MeetingDto meeting = restTemplate.getForObject("http://user-meeting/meeting/?id="+id, MeetingDto.class);
		return meeting;
	}
	public MeetingDto findMeetingByIdFallback(long id) {
		MeetingDto meeting = new MeetingDto(1L, "No available", new Date(), "No available", true, 1L);
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
		MeetingDto meetingNew = restTemplate.postForObject("http://user-meeting/meeting/", meeting, MeetingDto.class);
		return meetingNew;
	}
	
	public MeetingDto saveMeetingFallback(MeetingDto meeting) {
		MeetingDto meetingNew = new MeetingDto(1L, "No available", new Date(), "No available", true, 1L);
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
		ResponseEntity<MeetingDto> meetingNew = restTemplate.exchange("http://user-meeting/meeting/", HttpMethod.PUT,  requestUpdate, MeetingDto.class);
		return meetingNew.getBody();
	}
	public MeetingDto updateMeetingFallback(MeetingDto meeting) {
		MeetingDto meetingNew = new MeetingDto(1L, "No available", new Date(), "No available", true, 1L);
		return meetingNew;
	}
	

	@Override
	public void deleteStudentMeeting(Long idMeeting) {
		restTemplate.delete("http://user-meeting/meeting/?id="+idMeeting);

	}

	@Override
	public void deleteAllStudentMeetings(Long idStudent) {
		restTemplate.delete("http://user-meeting/meeting/deleteAllStudentMeetings/?id="+idStudent);

	}

}
