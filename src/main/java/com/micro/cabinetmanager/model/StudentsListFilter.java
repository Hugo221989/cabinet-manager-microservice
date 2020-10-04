package com.micro.cabinetmanager.model;

public class StudentsListFilter {

	int page;
	int size;
	String textToSearch;
	public StudentsListFilter() {

	}
	public StudentsListFilter(int page, int size, String textToSearch) {
		super();
		this.page = page;
		this.size = size;
		this.textToSearch = textToSearch;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getTextToSearch() {
		return textToSearch;
	}
	public void setTextToSearch(String textToSearch) {
		this.textToSearch = textToSearch;
	}
	
	
}
