package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.TicketHardwareModel_jar_86;
import com.csis3275.model.TicketHardwareRowMapper_jar_86;

@Service
public class TicketHardwareDAOImpl {
	JdbcTemplate jdbcTemplate;
	
	private final String GET_TICKET_HARDWARE = "SELECT * FROM ticketHardware";
	
	public TicketHardwareDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ArrayList<TicketHardwareModel_jar_86> getAllTickets(){
		ArrayList<TicketHardwareModel_jar_86> allTickets = new ArrayList<TicketHardwareModel_jar_86>();
		
		allTickets = (ArrayList<TicketHardwareModel_jar_86>) jdbcTemplate.query(GET_TICKET_HARDWARE, new TicketHardwareRowMapper_jar_86());
		
		return allTickets;
	}

}
