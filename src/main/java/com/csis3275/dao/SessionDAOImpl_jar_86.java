package com.csis3275.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.SessionModel_jar_86;
import com.csis3275.model.SessionRowMapper_jar_86;

@Service
public class SessionDAOImpl_jar_86 {
	JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_SESSION = "INSERT INTO sessions (id, email, role) VALUES (?,?,?)";
	private final String SQL_FIND_SESSION = "SELECT * FROM sessions WHERE id = ?";

	@Autowired
	public SessionDAOImpl_jar_86(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean createSession(SessionModel_jar_86 session) {
		return jdbcTemplate.update(SQL_INSERT_SESSION, session.getId(), session.getEmail(), session.getPosition()) > 0;
	}

	@SuppressWarnings("deprecation")
	public SessionModel_jar_86 getSession(String id) {

		try {
			return jdbcTemplate.queryForObject(SQL_FIND_SESSION, new Object[] { id }, new SessionRowMapper_jar_86());
		} catch (Exception e) {
			return null;
		}
	}
}
