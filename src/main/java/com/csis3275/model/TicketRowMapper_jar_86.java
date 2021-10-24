package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TicketRowMapper_jar_86 implements RowMapper<TicketModel_jar_86>{

	@Override
	public TicketModel_jar_86 mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketModel_jar_86 ticket = new TicketModel_jar_86();
		
		ticket.setId(rs.getInt("id"));
		ticket.setCreationDate(rs.getString("creationDate"));
		ticket.setStatus(rs.getString("status"));
		ticket.setUserCreator(rs.getString("userCreator"));
		ticket.setAssigneeUser(rs.getString("assigneeUser"));
		ticket.setTypeOfTicket(rs.getString("typeOfTicket"));
		ticket.setPriority(rs.getString("priority"));
		ticket.setPosition(rs.getString("position"));
		ticket.setHardwareToBeChanged(rs.getString("hardwareToBeChanged"));
//		ticket.setCommentsID(rs.getString("commentsID"));
		
		return ticket;
	}

}
