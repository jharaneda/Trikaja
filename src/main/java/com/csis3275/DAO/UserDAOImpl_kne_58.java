package com.csis3275.DAO;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl_kne_58 {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL_THE_USERS = "SELECT * FROM users";
	private final String SQL_CREATE_USER = "INSERT INTO users (name,email,numTickets) VALUES (?,?,?)";
	private final String SQL_DELETE_USER = "DELETE FROM employee WHERE userID = ?";
	private final String SQL_UPDATE_USER = "UPDATE users SET name = ?, email = ?, numTickets = ? WHERE userID = ?";
	private final String SQL_FIND_USER = "SELECT * FROM user WHERE userID = ?";
	
	@Autowired
	public UserDAOImpl_kne_58(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
