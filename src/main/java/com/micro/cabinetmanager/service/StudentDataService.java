package com.micro.cabinetmanager.service;

import com.micro.cabinetmanager.model.StudentDto;
import com.micro.cabinetmanager.model.StudentListDto;
import com.micro.cabinetmanager.model.StudentsListFilter;
import com.micro.cabinetmanager.model.StudentsPage;

public interface StudentDataService {
	
	StudentDto getStudentDataById(Long id);
	StudentDto getStudentDataByName(String name);
	StudentListDto getAllStudentsList();
	StudentsPage getAllStudentsPage(StudentsListFilter studentsListFilter);
	StudentDto createStudent(StudentDto student);
	StudentDto updateStudent(StudentDto student);
	void deleteStudent(Long id);

}
