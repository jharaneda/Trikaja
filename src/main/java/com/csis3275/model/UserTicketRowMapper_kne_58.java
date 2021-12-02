package com.csis3275.model;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class UserTicketRowMapper_kne_58 implements RowMapper<TicketUserJoinModel_kne_58> {
	
	@Override
	public TicketUserJoinModel_kne_58 mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketUserJoinModel_kne_58 ticketUser = new TicketUserJoinModel_kne_58();
		
		ticketUser.setId(rs.getLong("id"));
		ticketUser.setCreationDate(rs.getString("creationDate"));
		ticketUser.setStatus(rs.getString("status"));
		ticketUser.setUserCreator(rs.getString("userCreator"));
		ticketUser.setAssigneeUser(rs.getString("assigneeUser"));
		ticketUser.setTypeOfTicket(rs.getString("typeOfTicket"));
		ticketUser.setPriority(rs.getString("priority"));
		ticketUser.setPosition(rs.getString("position"));
		ticketUser.setHardwareToBeChanged(rs.getString("hardwareToBeChanged"));
		ticketUser.setUserID(rs.getInt("userID"));
		ticketUser.setName(rs.getString("name"));
		ticketUser.setEmail(rs.getString("email"));
		ticketUser.setNumTickets(rs.getInt("numTickets"));
		ticketUser.setPassword(rs.getString("password"));
		
		return ticketUser;
	}

}
