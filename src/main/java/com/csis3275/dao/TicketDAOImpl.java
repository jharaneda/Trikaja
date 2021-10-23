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
	
	private final String GET_TICKET_HARDWARE = "SELECT * FROM ticketHardware";
	
	@Autowired
	public TicketDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ArrayList<TicketModel_jar_86> getAllTickets(){
		ArrayList<TicketModel_jar_86> allTickets = new ArrayList<TicketModel_jar_86>();
		
		allTickets = (ArrayList<TicketModel_jar_86>) jdbcTemplate.query(GET_TICKET_HARDWARE, new TicketRowMapper_jar_86());
		
		return allTickets;
	}

}
