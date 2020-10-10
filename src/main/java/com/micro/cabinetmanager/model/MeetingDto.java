package com.micro.cabinetmanager.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MeetingDto {

	private Long id;
	private String title;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date start;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date end;
	private String colorPrimary;
	private String colorSecondary;
	private String description;
	private String location;
	private boolean mode;
	private Long idStudent;
	private String studentName;
	public MeetingDto() {

	}
	
	
	
	public MeetingDto(Long id, String title, Date start, Date end, String colorPrimary, String colorSecondary,
			String description, String location, boolean mode, Long idStudent, String studentName) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.colorPrimary = colorPrimary;
		this.colorSecondary = colorSecondary;
		this.description = description;
		this.location = location;
		this.mode = mode;
		this.idStudent = idStudent;
		this.studentName = studentName;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

	public String getColorPrimary() {
		return colorPrimary;
	}



	public void setColorPrimary(String colorPrimary) {
		this.colorPrimary = colorPrimary;
	}



	public String getColorSecondary() {
		return colorSecondary;
	}



	public void setColorSecondary(String colorSecondary) {
		this.colorSecondary = colorSecondary;
	}



	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}
