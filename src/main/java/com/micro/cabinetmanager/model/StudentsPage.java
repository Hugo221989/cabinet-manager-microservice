package com.micro.cabinetmanager.model;


public class StudentsPage {
	RestResponsePage<StudentDto> studentsPage;

	public StudentsPage() {

	}

	public StudentsPage(RestResponsePage<StudentDto> studentsPage) {
		super();
		this.studentsPage = studentsPage;
	}

	public RestResponsePage<StudentDto> getStudentsPage() {
		return studentsPage;
	}

	public void setStudentsPage(RestResponsePage<StudentDto> studentsPage) {
		this.studentsPage = studentsPage;
	}

	
}
