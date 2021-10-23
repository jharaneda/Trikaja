package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.TicketModel_jar_86;
import com.csis3275.model.TicketRowMapper_jar_86;

@Service
public class TicketDAOImpl {
	JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_TICKET = "SELECT * FROM tickets";
	private final String SQL_CREATE_TICKET = "insert into tickets (creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, comments) values "
			+ "(?,?,?,?,?,?,?,?,?)";
	
	@Autowired
	public TicketDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ArrayList<TicketModel_jar_86> getAllTickets(){
		ArrayList<TicketModel_jar_86> allTickets = new ArrayList<TicketModel_jar_86>();
		
		allTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_TICKET, new TicketRowMapper_jar_86());
		
		return allTickets;
	}
	
	public boolean createTicket(TicketModel_jar_86 createTicket) {
		return jdbcTemplate.update(SQL_CREATE_TICKET, createTicket.getCreationDate(), createTicket.getStatus(), createTicket.getUserCreator(), createTicket.getAssigneeUser(), createTicket.getTypeOfTicket(), createTicket.getPriority(), createTicket.getPosition(), createTicket.getHardwareToBeChanged(), createTicket.getComments()) > 0;
	}
}
