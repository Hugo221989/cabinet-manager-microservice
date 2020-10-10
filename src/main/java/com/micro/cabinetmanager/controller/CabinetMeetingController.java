package com.micro.cabinetmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.micro.cabinetmanager.model.MeetingDto;
import com.micro.cabinetmanager.model.MeetingListDto;
import com.micro.cabinetmanager.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointment")
@Api(value = "VALUE", description = "Administracion de citas de alumnos")
public class CabinetMeetingController {
	@Autowired
	private MeetingService meetingService;
	
	@GetMapping("/getAllMeetings")
	@ApiOperation(value = "Obtener todas las reuniones de un alumno", notes = "Este servicio web obtiene una lista de todas las reuniones de un alumno.", response = MeetingListDto.class, responseContainer = "MeetingListDto")
	public MeetingListDto getAllMeetings() {
		return this.meetingService.getAllMeetings();
	}

	@GetMapping("/getAllMeetingsByStudentId")
	@ApiOperation(value = "Obtener todas las reuniones de un alumno", notes = "Este servicio web obtiene una lista de todas las reuniones de un alumno.", response = MeetingListDto.class, responseContainer = "MeetingListDto")
	public MeetingListDto getAllMeetingsByStudentId(@ApiParam(name ="studentId", example="1", value = "studentId", required = false)@RequestParam(required = false) Long studentId) {
		return this.meetingService.getAllMeetingsByStudentId(studentId);
	}

	@GetMapping("/")
	@ApiOperation(value = "Obtener la información de una reunion", notes = "Este servicio web obtiene los datos de una reunión de un alumnor.", response = MeetingDto.class, responseContainer = "MeetingDto")
	public ResponseEntity<MeetingDto> getMeetingData(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false) Long id) {
		MeetingDto meeting;
		try {
			meeting = this.meetingService.findMeetingById(id);
		}catch(Exception e) {
			return new ResponseEntity("No encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(meeting);
	}
	
	@PostMapping("/")
	@ApiOperation(value = "Crear reunion", notes = "Este servicio web crea una reunión en el registro.", response = MeetingDto.class, responseContainer = "MeetingDto")
	public ResponseEntity<MeetingDto> createStudentMeeting(@ApiParam(value = "meeting", required = false)@RequestBody MeetingDto meeting) {
		MeetingDto meetingAfter = this.meetingService.saveMeeting(meeting);
		return ResponseEntity.ok(meetingAfter);
	}
	
	@PutMapping("/")
	@ApiOperation(value = "Actualizar reunión", notes = "Este servicio web actualiza los datos datos de la reunión de un alumno.", response = MeetingDto.class, responseContainer = "MeetingDto")
	public ResponseEntity<MeetingDto> updateStudentMeeting(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false) Long id,
														@ApiParam(value = "meeting", required = false)@RequestBody MeetingDto meeting) {
		MeetingDto studentAfter =  this.meetingService.saveMeeting(meeting);
		return ResponseEntity.ok(studentAfter);
	}
	
	@DeleteMapping("/")
	@ApiOperation(value = "Eliminar una reunion de un alumno", notes = "Este servicio web elimina la reunión seleccionada del alumno.", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteStudentMeeting(@ApiParam(name ="idMeeting", example="1", value = "idMeeting", required = false)@RequestParam(required = false) Long idMeeting) {
		this.meetingService.deleteStudentMeeting(idMeeting);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deleteAllStudentMeetings")
	@ApiOperation(value = "Eliminar todas las reuniones de un alumno", notes = "Este servicio web elimina todas las reuniones de un alumno", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteAllStudentMeetings(@ApiParam(name ="id", example="1", value = "idStudent", required = false)@RequestParam(required = false) Long idStudent) {
		this.meetingService.deleteAllStudentMeetings(idStudent);
		return ResponseEntity.noContent().build();
	}
}
