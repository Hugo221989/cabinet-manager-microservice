package com.micro.cabinetmanager.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MeetingDto {

	private Long id;

	private String name;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date date;
	
	private String reason;
	
	private boolean mode;
	
	private Long idStudent;

	public MeetingDto() {

	}

	public MeetingDto(Long id, String name, Date date, String reason, boolean mode, Long idStudent) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.reason = reason;
		this.mode = mode;
		this.idStudent = idStudent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}
	
	
	
}
