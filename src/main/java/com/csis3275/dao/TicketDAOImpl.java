package com.csis3275.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.csis3275.model.TicketModel_jar_86;
import com.csis3275.model.TicketRowMapper_jar_86;

@Service
public class TicketDAOImpl {
	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_TICKETS = "SELECT * FROM tickets";
	private final String SQL_CREATE_TICKET = "insert into tickets (creationDate, status, userCreator, assigneeUser, typeOfTicket, priority, position, hardwareToBeChanged) values "
			+ "(?,?,?,?,?,?,?,?)";
	private final String SQL_GET_ONE_TICKET = "SELECT * FROM tickets where id = ?";
	private final String SQL_DELETE_ONE_TICKET = "DELETE FROM tickets WHERE ID = ?";
	private final String SQL_UPDATE_TICKET = "UPDATE tickets SET status = ?, userCreator = ?, assigneeUser = ?, typeOfTicket = ?, priority = ?, position = ?, hardwareToBeChanged = ? WHERE id = ?";
	private final String SQL_UPDATE_TICKET_USER = "UPDATE tickets SET position = ? WHERE id = ?";
	private final String SQL_GET_TICKET_BY_USER = "SELECT * FROM tickets where userCreator = ?";
	private final String SQL_GET_TICKET_BY_HARDWARE ="SELECT * FROM tickets WHERE status = ? AND typeOfTicket = ? and hardwareToBeChanged IS NOT NULL";
	private final String SQL_GET_OPEN_TICKETS = "SELECT * FROM tickets where status = 'Open'";
	private final String SQL_GET_PENDING_TICKETS = "SELECT * FROM tickets where status = 'Pending'";
	private final String SQL_GET_SOLVED_TICKETS = "SELECT * FROM tickets where status = 'Solved'";
	private final String SQL_COUNT_TICKETS = "SELECT COUNT(STATUS) FROM tickets where userCreator = ?";


	@Autowired
	public TicketDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<TicketModel_jar_86> getAllTickets() {
		ArrayList<TicketModel_jar_86> allTickets = new ArrayList<TicketModel_jar_86>();

		allTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_TICKETS, new TicketRowMapper_jar_86());

		return allTickets;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<TicketModel_jar_86> getOpenHardwareTickets(String status, String type){
		ArrayList<TicketModel_jar_86> openHardwareTickets = new ArrayList<TicketModel_jar_86>();
		openHardwareTickets = (ArrayList<TicketModel_jar_86> ) jdbcTemplate.query(SQL_GET_TICKET_BY_HARDWARE,new Object[] {status, type},new TicketRowMapper_jar_86());
		
		return openHardwareTickets;
	}
	
	public ArrayList<TicketModel_jar_86> getAllOpenTickets() {
		ArrayList<TicketModel_jar_86> allOpenTickets = new ArrayList<TicketModel_jar_86>();

		allOpenTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_OPEN_TICKETS, new TicketRowMapper_jar_86());

		return allOpenTickets;
	}
	
	public ArrayList<TicketModel_jar_86> getAllPendingTickets() {
		ArrayList<TicketModel_jar_86> allPendingTickets = new ArrayList<TicketModel_jar_86>();

		allPendingTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_PENDING_TICKETS, new TicketRowMapper_jar_86());

		return allPendingTickets;
	}
	
	public ArrayList<TicketModel_jar_86> getAllSolvedTickets() {
		ArrayList<TicketModel_jar_86> allSolvedTickets = new ArrayList<TicketModel_jar_86>();

		allSolvedTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_SOLVED_TICKETS, new TicketRowMapper_jar_86());

		return allSolvedTickets;

	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<TicketModel_jar_86> getTicketByUser(String userCreator) {
		ArrayList<TicketModel_jar_86> ticketByUser = new ArrayList<TicketModel_jar_86>();

		ticketByUser = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(SQL_GET_TICKET_BY_USER, new Object[] { userCreator }, new TicketRowMapper_jar_86());

		return ticketByUser;
	}

	public boolean createTicket(TicketModel_jar_86 createTicket) {
		return jdbcTemplate.update(SQL_CREATE_TICKET, createTicket.getCreationDate(), createTicket.getStatus(),
				createTicket.getUserCreator(), createTicket.getAssigneeUser(), createTicket.getTypeOfTicket(),
				createTicket.getPriority(), createTicket.getPosition(), createTicket.getHardwareToBeChanged()) > 0;
	}
	
	public Long save(TicketModel_jar_86 createTicket) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SQL_CREATE_TICKET, new String[] {"id"});
			ps.setString(1, createTicket.getCreationDate());
			ps.setString(2, createTicket.getStatus());
			ps.setString(3, createTicket.getUserCreator());
			ps.setString(4, createTicket.getAssigneeUser());
			ps.setString(5, createTicket.getTypeOfTicket());
			ps.setString(6, createTicket.getPriority());
			ps.setString(7, createTicket.getPosition());
			ps.setString(8, createTicket.getHardwareToBeChanged());
			return ps;
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@SuppressWarnings("deprecation")
	public TicketModel_jar_86 getTicketById(Long id) {
		return jdbcTemplate.queryForObject(SQL_GET_ONE_TICKET, new Object[] { id }, new TicketRowMapper_jar_86());
	}

	public boolean deleteTicket(Long id) {
		return jdbcTemplate.update(SQL_DELETE_ONE_TICKET, id) > 0;
	}

	public boolean updateTicket(TicketModel_jar_86 updatedTicket) {
		return jdbcTemplate.update(SQL_UPDATE_TICKET, updatedTicket.getStatus(), updatedTicket.getUserCreator(),
				updatedTicket.getAssigneeUser(), updatedTicket.getTypeOfTicket(), updatedTicket.getPriority(),
				updatedTicket.getPosition(), updatedTicket.getHardwareToBeChanged(), updatedTicket.getId()) > 0;
	}
	
	public boolean updateTicketUserView(TicketModel_jar_86 updatedTicket) {
		return jdbcTemplate.update(SQL_UPDATE_TICKET_USER, updatedTicket.getPosition(), updatedTicket.getId()) > 0;
	}
	
}
