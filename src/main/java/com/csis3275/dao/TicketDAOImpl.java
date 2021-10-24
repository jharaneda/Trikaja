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

	private final String SQL_GET_TICKETS = "SELECT * FROM tickets";
	private final String SQL_CREATE_TICKET = "insert into tickets (creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged, commentsID) values "
			+ "(?,?,?,?,?,?,?,?,?)";
	private final String SQL_GET_ONE_TICKET = "SELECT * FROM tickets where id = ?";
	private final String SQL_DELETE_ONE_TICKET = "DELETE FROM tickets WHERE ID = ?";
	private final String SQL_UPDATE_TICKET = "UPDATE tickets SET status = ?, userCreator = ?, assigneeUser = ?, typeOfTicket = ?, priority = ?, position = ?, hardwareToBeChanged = ?, commentsID = ? WHERE id = ?";

	@Autowired
	public TicketDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<TicketModel_jar_86> getAllTickets() {
		ArrayList<TicketModel_jar_86> allTickets = new ArrayList<TicketModel_jar_86>();

		allTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_TICKETS, new TicketRowMapper_jar_86());

		return allTickets;
	}

	public boolean createTicket(TicketModel_jar_86 createTicket) {
		return jdbcTemplate.update(SQL_CREATE_TICKET, createTicket.getCreationDate(), createTicket.getStatus(),
				createTicket.getUserCreator(), createTicket.getAssigneeUser(), createTicket.getTypeOfTicket(),
				createTicket.getPriority(), createTicket.getPosition(), createTicket.getHardwareToBeChanged()) > 0;
	}

	@SuppressWarnings("deprecation")
	public TicketModel_jar_86 getTicketById(int id) {
		return jdbcTemplate.queryForObject(SQL_GET_ONE_TICKET, new Object[] { id }, new TicketRowMapper_jar_86());
	}

	public boolean deleteTicket(int id) {
		return jdbcTemplate.update(SQL_DELETE_ONE_TICKET, id) > 0;
	}

	public boolean updateTicket(TicketModel_jar_86 updatedTicket) {
		//ANTES DE QUITAR COMMENT
		//		return jdbcTemplate.update(SQL_UPDATE_TICKET, updatedTicket.getStatus(), updatedTicket.getUserCreator(),
//				updatedTicket.getAssigneeUser(), updatedTicket.getTypeOfTicket(), updatedTicket.getPriority(),
//				updatedTicket.getPosition(), updatedTicket.getHardwareToBeChanged(), updatedTicket.getCommentsID(), updatedTicket.getId()) > 0;
		
		return jdbcTemplate.update(SQL_UPDATE_TICKET, updatedTicket.getStatus(), updatedTicket.getUserCreator(),
				updatedTicket.getAssigneeUser(), updatedTicket.getTypeOfTicket(), updatedTicket.getPriority(),
				updatedTicket.getPosition(), updatedTicket.getHardwareToBeChanged(), updatedTicket.getId()) > 0;
	}
	
}
