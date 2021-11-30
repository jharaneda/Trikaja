package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.PreAnswerRowMapper_jar_86;
import com.csis3275.model.PredefinedAnswersModel_jar_86;

@Service
public class PreAnswersDAOImpl_jar_86 {
	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ANSWERS = "SELECT * FROM predefinedAnswers";
	private final String SQL_GET_ONE_ANSWER = "SELECT * FROM comments where id = ?";
	private final String SQL_INSERT_COMMENT = "insert into predefinedAnswers (id, name, comment) values (?,?,?);";
	private final String SQL_UPDATE_ANSWER = "UPDATE predefinedAnswers SET name = ?, comment = ?WHERE id = ?";
	private final String SQL_DELETE_ONE_ANSWER = "DELETE FROM predefinedAnswers WHERE id = ?";

	@Autowired
	public PreAnswersDAOImpl_jar_86(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<PredefinedAnswersModel_jar_86> getAllAnswers() {
		ArrayList<PredefinedAnswersModel_jar_86> allAnswers = new ArrayList<PredefinedAnswersModel_jar_86>();

		allAnswers = (ArrayList<PredefinedAnswersModel_jar_86>) jdbcTemplate.query(SQL_GET_ANSWERS, new PreAnswerRowMapper_jar_86());

		return allAnswers;
	}

	public boolean createAnswer(PredefinedAnswersModel_jar_86 answer) {
		return jdbcTemplate.update(SQL_INSERT_COMMENT, answer.getName(), answer.getComment()) > 0;
	}

	@SuppressWarnings("deprecation")
	public PredefinedAnswersModel_jar_86 getAnswerById(Long id) {
		return jdbcTemplate.queryForObject(SQL_GET_ONE_ANSWER, new Object[] { id }, new PreAnswerRowMapper_jar_86());
	}

	public boolean updateAnswer(PredefinedAnswersModel_jar_86 updatedAnswer) {
		return jdbcTemplate.update(SQL_UPDATE_ANSWER, updatedAnswer.getName(), updatedAnswer.getComment(), updatedAnswer.getId()) > 0;
	}
	
	public boolean deleteAnswer(Long id) {
		return jdbcTemplate.update(SQL_DELETE_ONE_ANSWER, id) > 0;
	}
}
