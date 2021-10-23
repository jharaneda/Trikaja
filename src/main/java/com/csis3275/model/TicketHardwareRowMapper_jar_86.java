package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TicketHardwareRowMapper_jar_86 implements RowMapper<TicketHardwareModel_jar_86>{

	@Override
	public TicketHardwareModel_jar_86 mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketHardwareModel_jar_86 ticketHardware = new TicketHardwareModel_jar_86();
		
		ticketHardware.setId(rs.getInt("id"));
		ticketHardware.setCreationDate(rs.getString("creationDate"));
		ticketHardware.setStatus(rs.getString("status"));
		ticketHardware.setUserCreator(rs.getString("userCreator"));
		ticketHardware.setAssigneeUser(rs.getString("assigneeUser"));
		ticketHardware.setTypeOfTicket(rs.getString("typeOfTicket"));
		ticketHardware.setPriority(rs.getString("priority"));
		ticketHardware.setPosition(rs.getString("position"));
		ticketHardware.setHardwareToBeChanged(rs.getString("hardwareToBeChanged"));
		ticketHardware.setComments(rs.getString("comments"));
		
		return ticketHardware;
	}

}
