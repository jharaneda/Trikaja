package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SessionRowMapper_jar_86 implements RowMapper<SessionModel_jar_86> {

	@Override
	public SessionModel_jar_86 mapRow(ResultSet rs, int rowNum) throws SQLException {
		SessionModel_jar_86 session = new SessionModel_jar_86();

		session.setId(rs.getString("id"));
		session.setEmail(rs.getString("email"));
		session.setPosition(rs.getString("position"));
		return session;
	}
}
