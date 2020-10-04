package com.micro.cabinetmanager.model;

import java.util.List;

public class StudentListDto {
	List<StudentDto> studentList;

	public StudentListDto() {
		
	}

	public StudentListDto(List<StudentDto> studentList) {
		super();
		this.studentList = studentList;
	}

	public List<StudentDto> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentDto> studentList) {
		this.studentList = studentList;
	}
	
	
}
