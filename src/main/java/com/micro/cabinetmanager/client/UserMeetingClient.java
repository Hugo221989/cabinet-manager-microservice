package com.micro.cabinetmanager.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.micro.cabinetmanager.model.MeetingDto;
import com.micro.cabinetmanager.model.MeetingListDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@FeignClient(value="user-meeting")
@RequestMapping("/meeting")
public interface UserMeetingClient {
	
	@GetMapping("/getAllMeetings")
	@ApiOperation(value = "Obtener todas las reuniones", notes = "Este servicio web obtiene todas las reuniones.", response = MeetingListDto.class, responseContainer = "MeetingListDto")
	public MeetingListDto getAllMeetings();
	
	@GetMapping("/getAllMeetingsByStudentId")
	@ApiOperation(value = "Obtener todas las reuniones de un alumno", notes = "Este servicio web obtiene una lista de todas las reuniones de un alumno.", response = MeetingListDto.class, responseContainer = "MeetingListDto")
	public MeetingListDto getAllMeetingsByStudentId(@ApiParam(name ="id", example="1", value = "studentId", required = false)@RequestParam(required = false, value="studentId") Long studentId);

	@GetMapping("/")
	@ApiOperation(value = "Obtener la información de una reunion", notes = "Este servicio web obtiene los datos de una reunión de un alumnor.", response = MeetingDto.class, responseContainer = "MeetingDto")
	public ResponseEntity<MeetingDto> getMeetingData(@ApiParam(name ="meetingId", example="1", value = "id", required = false)@RequestParam(required = false, value="meetingId") Long meetingId);
	
	@PostMapping("/")
	@ApiOperation(value = "Crear reunion", notes = "Este servicio web crea una reunión en el registro.", response = MeetingDto.class, responseContainer = "MeetingDto")
	public ResponseEntity<MeetingDto> createStudentMeeting(@ApiParam(value = "meeting", required = false)@RequestBody MeetingDto meeting);
	
	@PutMapping("/")
	@ApiOperation(value = "Actualizar reunión", notes = "Este servicio web actualiza los datos datos de la reunión de un alumno.", response = MeetingDto.class, responseContainer = "MeetingDto")
	public ResponseEntity<MeetingDto> updateStudentMeeting(@ApiParam(name ="id", example="1", value = "id", required = false)@RequestParam(required = false, value="id") Long id,
														@ApiParam(value = "meeting", required = false)@RequestBody MeetingDto meeting);
	
	@DeleteMapping("/")
	@ApiOperation(value = "Eliminar una reunion de un alumno", notes = "Este servicio web elimina la reunión seleccionada del alumno.", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteStudentMeeting(@ApiParam(name ="id", example="1", value = "idMeeting", required = false)@RequestParam(required = false, value="idMeeting") Long idMeeting);
	
	@DeleteMapping("/deleteAllStudentMeetings")
	@ApiOperation(value = "Eliminar todas las reuniones de un alumno", notes = "Este servicio web elimina todas las reuniones de un alumno", response = Void.class, responseContainer = "Void")
	public ResponseEntity<Void> deleteAllStudentMeetings(@ApiParam(name ="id", example="1", value = "idStudent", required = false)@RequestParam(required = false, value="idStudent") Long idStudent);
}
