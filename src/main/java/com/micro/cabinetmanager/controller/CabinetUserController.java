package com.micro.cabinetmanager.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.cabinetmanager.model.RestResponsePage;
import com.micro.cabinetmanager.model.StudentDto;
import com.micro.cabinetmanager.model.StudentListDto;
import com.micro.cabinetmanager.model.StudentsListFilter;
import com.micro.cabinetmanager.model.StudentsPage;
import com.micro.cabinetmanager.service.StudentDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@Api(value = "VALUE", description = "Administracion de alumnos")
public class CabinetUserController {
	
	@Autowired
	private StudentDataService userDataService;

	@GetMapping("/")
	@ApiOperation(value = "Obtener un alumno", notes = "Este servicio web obtiene los datos de un alumno en particular.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> getStudentData(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false) Long id) {
		StudentDto student;
		try {
			student = this.userDataService.getStudentDataById(id);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/byName")
	@ApiOperation(value = "Obtener un alumno", notes = "Este servicio web obtiene los datos de un alumno en particular filtrando por el nombre.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> getStudentDataByName(@ApiParam(name ="name", example="1", value = "name", required = false)@RequestParam(required = false) String name) {
		StudentDto student;
		try {
			student = this.userDataService.getStudentDataByName(name);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(student);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Obtener todos los alumnos", notes = "Este servicio web obtiene una lista de todos los alumnos.", response = StudentsPage.class, responseContainer = "StudentsPage")
	public ResponseEntity<RestResponsePage<StudentDto>> getAllStudents(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@ApiParam(value = "textToSearch", required = false) @RequestParam(required = false) String textToSearch) throws IOException {
		//StudentListDto studentList;
		StudentsListFilter studentsListFilter = new StudentsListFilter(page, size, textToSearch);
		StudentsPage studentsPageWrapper = new StudentsPage();
		try {
			studentsPageWrapper = this.userDataService.getAllStudents(studentsListFilter);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(studentsPageWrapper.getStudentsPage());
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Crear alumno", notes = "Este servicio web crea un nuevo alumno en el registro.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> createStudent(@ApiParam(value = "student", required = false)@RequestBody StudentDto student) {
		StudentDto studentDto;
		try {
			studentDto = this.userDataService.createStudent(student);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(studentDto);
	}
	

	@PutMapping("/")
	@ApiOperation(value = "Actualizar alumno", notes = "Este servicio web actualiza los datos personales de un alumno.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> updateStudent(@ApiParam(value = "student", required = false)@RequestBody StudentDto student) {
		StudentDto studentDto;
		try {
			studentDto = this.userDataService.updateStudent(student);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(studentDto);
	}
	
	@DeleteMapping("/")
	@ApiOperation(value = "Eliminar alumno", notes = "Este servicio web elimina el alumno y su diagnostico asociado.", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteStudent(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false) Long id) {
		this.userDataService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
	
}
