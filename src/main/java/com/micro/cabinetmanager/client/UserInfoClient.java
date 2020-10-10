package com.micro.cabinetmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.micro.cabinetmanager.model.StudentDto;
import com.micro.cabinetmanager.model.StudentListDto;
import com.micro.cabinetmanager.model.StudentsPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@FeignClient(value="user-info")
@RequestMapping("/info")
public interface UserInfoClient {

	@GetMapping("/allList")
	@ApiOperation(value = "Obtener toda la lista de los alumnos", notes = "Este servicio web obtiene una lista de todos los alumnos.", response = StudentListDto.class, responseContainer = "StudentListDto")
	public StudentListDto getAllStudentsList();
	
	@GetMapping("/allPage")
	@ApiOperation(value = "Obtener todos los alumnos paginada", notes = "Este servicio web obtiene una lista de todos los alumnos paginada.", response = Page.class, responseContainer = "Page")
	public StudentsPage getAllStudentsPage(@ApiParam(name ="page", example="1", value = "1", required = false)@RequestParam(required = false, defaultValue = "0", value="page") int page,
			@ApiParam(name ="size", example="1", value = "5", required = false)@RequestParam(required = false, defaultValue = "5", value="size") int size,
			@ApiParam(name ="textToSearch", example="Ramon", value = "Ramon", required = false)@RequestParam(required = false, value="textToSearch") String textToSearch);

	@GetMapping("/")
	@ApiOperation(value = "Obtener un alumno", notes = "Este servicio web obtiene los datos de un alumno en particular.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> getStudentData(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false, value="id") Long id);
	
	@GetMapping("/byName")
	@ApiOperation(value = "Obtener un alumno", notes = "Este servicio web obtiene los datos de un alumno en particular.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> getStudentDataByName(@ApiParam(name ="name", example="1", value = "nombre", required = false)@RequestParam(required = false, value="name") String name);
	
	@PostMapping("/")
	@ApiOperation(value = "Crear alumno", notes = "Este servicio web crea un nuevo alumno en el registro.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> createStudent(@ApiParam(value = "student", required = false)@RequestBody StudentDto student);
	
	@PutMapping("/")
	@ApiOperation(value = "Actualizar alumno", notes = "Este servicio web actualiza los datos personales de un alumno.", response = StudentDto.class, responseContainer = "StudentDto")
	public ResponseEntity<StudentDto> updateStudent(@ApiParam(value = "student", required = false)@RequestBody StudentDto student);
	
	@DeleteMapping("/")
	@ApiOperation(value = "Eliminar alumno", notes = "Este servicio web elimina el alumno y su diagnostico asociado.", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteStudent(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false, value="id") Long id);
	
}
