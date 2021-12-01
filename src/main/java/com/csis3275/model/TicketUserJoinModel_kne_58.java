package com.csis3275.model;

public class TicketUserJoinModel_kne_58 {
	
	protected int userID;
	protected String name;
	protected String email;
	protected int numTickets;
	private String password;	
	private Long id;
	private String creationDate;
	private String status;
	private String userCreator;
	private String assigneeUser;
	private String typeOfTicket;
	private String priority;
	private String position;
	private String hardwareToBeChanged;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserCreator() {
		return userCreator;
	}
	public void setUserCreator(String userCreator) {
		this.userCreator = userCreator;
	}
	public String getAssigneeUser() {
		return assigneeUser;
	}
	public void setAssigneeUser(String assigneeUser) {
		this.assigneeUser = assigneeUser;
	}
	public String getTypeOfTicket() {
		return typeOfTicket;
	}
	public void setTypeOfTicket(String type) {
		this.typeOfTicket = type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getHardwareToBeChanged() {
		return hardwareToBeChanged;
	}
	public void setHardwareToBeChanged(String hardwareToBeChanged) {
		this.hardwareToBeChanged = hardwareToBeChanged;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumTickets() {
		return numTickets;
	}
	public void setNumTickets(int numTickets) {
		this.numTickets = numTickets;
	}
}
