package com.micro.cabinetmanager.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.cabinetmanager.client.UserInfoClient;
import com.micro.cabinetmanager.model.DiagnosisDto;
import com.micro.cabinetmanager.model.RestResponsePage;
import com.micro.cabinetmanager.model.StudentDto;
import com.micro.cabinetmanager.model.StudentListDto;
import com.micro.cabinetmanager.model.StudentsListFilter;
import com.micro.cabinetmanager.model.StudentsPage;
import com.micro.cabinetmanager.service.StudentDataService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class StudentDataServiceImpl implements StudentDataService {
	
	//private static final String URL_INFO_SERVICE = "http://192.168.99.101:8081/user-info/info/";
	//private static final String URL_INFO_SERVICE = "http://localhost:8081/info/";
//	@Autowired 
//	private RestTemplate restTemplate;
	
	@Autowired
	private UserInfoClient userInfoClient; 

	@Override
	@HystrixCommand(fallbackMethod="getStudentDataFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="30000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public StudentDto getStudentDataById(Long id) {
		//StudentDto student = restTemplate.getForObject(URL_INFO_SERVICE+"?id="+id, StudentDto.class);
		ResponseEntity<StudentDto> studentDtoEntity = this.userInfoClient.getStudentData(id); 
		StudentDto student = studentDtoEntity.getBody();
		return student;
	}
	public StudentDto getStudentDataFallback(Long id) {
		DiagnosisDto diagnosis = new DiagnosisDto(id, "No disponible", "No disponible");
		StudentDto student = new StudentDto(id, "No disponible", "No disponible", null, "No disponible", diagnosis);
		return student;
	}
	
	
	@Override
	@HystrixCommand(fallbackMethod="getStudentDataByNameFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public StudentDto getStudentDataByName(String name) {
		//StudentDto student = restTemplate.getForObject(URL_INFO_SERVICE+"byName/?name="+name, StudentDto.class);
		ResponseEntity<StudentDto> studentDtoEntity = this.userInfoClient.getStudentDataByName(name);
		return studentDtoEntity.getBody();
	}
	public StudentDto getStudentDataByNameFallback(String name) {
		DiagnosisDto diagnosis = new DiagnosisDto(1L, "No disponible", "No disponible");
		StudentDto student = new StudentDto(1L, "No disponible", "No disponible", null, "No disponible", diagnosis);
		return student;
	}
	
	
	@Override
	@HystrixCommand(fallbackMethod="getAllStudentsListFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="10000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public StudentListDto getAllStudentsList() {
		//StudentListDto studentListDto = restTemplate.getForObject(URL_INFO_SERVICE+"allList", StudentListDto.class);
		StudentListDto studentDtoList = this.userInfoClient.getAllStudentsList();
		return studentDtoList;
	}
	public StudentListDto getAllStudentsListFallback() {
		List<StudentDto> studentList = new ArrayList<StudentDto>();
		StudentListDto studentListDto = new StudentListDto(studentList);
		return studentListDto;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@HystrixCommand(fallbackMethod="getAllStudentsPageFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="10000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public StudentsPage getAllStudentsPage(StudentsListFilter studentsListFilter) {
		String dataParams = "page="+studentsListFilter.getPage()+"&size="+studentsListFilter.getSize()+"&textToSearch="+studentsListFilter.getTextToSearch();
		//StudentsPage studentsPageWrapper = restTemplate.getForObject(URL_INFO_SERVICE+"allPage?"+dataParams, StudentsPage.class);
		StudentsPage studentsPageWrapper = this.userInfoClient.getAllStudentsPage(studentsListFilter.getPage(), studentsListFilter.getSize(), studentsListFilter.getTextToSearch());
		return studentsPageWrapper;
	}
	public StudentsPage getAllStudentsPageFallback(StudentsListFilter studentsListFilter) {
		List<StudentDto> studentsEmptyList = new ArrayList<StudentDto>();
		RestResponsePage<StudentDto> studentsPage = new RestResponsePage<>(studentsEmptyList);
		StudentsPage studentsPageWrapper = new StudentsPage(studentsPage);
		return studentsPageWrapper;
	}
	
	
	@Override
	@HystrixCommand(fallbackMethod="createStudentFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public StudentDto createStudent(StudentDto student) {
		//StudentDto studentAfter = restTemplate.postForObject(URL_INFO_SERVICE, student, StudentDto.class);
		ResponseEntity<StudentDto> studentAfter = this.userInfoClient.createStudent(student);
		return studentAfter.getBody();
	}
	public StudentDto createStudentFallback(StudentDto student) {
		DiagnosisDto diagnosis = new DiagnosisDto(1L, "No disponible", "No disponible");
		StudentDto studentReturn = new StudentDto(1L, "No disponible", "No disponible", null, "No disponible", diagnosis);
		return studentReturn;
	}
	
	
	@Override
	@HystrixCommand(fallbackMethod="updateStudentFallback", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000")
	})
	public StudentDto updateStudent(StudentDto student) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<StudentDto> requestUpdate = new HttpEntity<>(student, headers);
		//ResponseEntity<StudentDto> studentAfter =  restTemplate.exchange(URL_INFO_SERVICE, HttpMethod.PUT,  requestUpdate, StudentDto.class);
		ResponseEntity<StudentDto> studentAfter = this.userInfoClient.updateStudent(student);
		return studentAfter.getBody();
	}
	public StudentDto updateStudentFallback(StudentDto student) {
		DiagnosisDto diagnosis = new DiagnosisDto(1L, "No disponible", "No disponible");
		StudentDto studentReturn = new StudentDto(1L, "No disponible", "No disponible", null, "No disponible", diagnosis);
		return studentReturn;
	}
	
	public void deleteStudent(Long id) {
		//restTemplate.delete(URL_INFO_SERVICE+"?id="+id);
		this.userInfoClient.deleteStudent(id);
	}

}
