package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PreAnswerRowMapper_jar_86 implements RowMapper<PredefinedAnswersModel_jar_86>{

	@Override
	public PredefinedAnswersModel_jar_86 mapRow(ResultSet rs, int rowNum) throws SQLException {
		PredefinedAnswersModel_jar_86 answer = new PredefinedAnswersModel_jar_86();
		
		answer.setId(rs.getInt("id"));
		answer.setName(rs.getString("name"));
		answer.setComment(rs.getString("comment"));
		
		return answer;
	}
}
