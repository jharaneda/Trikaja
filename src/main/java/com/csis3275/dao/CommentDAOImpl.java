package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.CommentsModel_jar_86;
import com.csis3275.model.CommentsRowMapper_jar_86;

@Service
public class CommentDAOImpl {
	JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_COMMENTS = "SELECT * FROM comments";
	private final String SQL_GET_ONE_COMMENT = "SELECT * FROM comments where ticketID = ?";
	
	@Autowired
	public CommentDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ArrayList<CommentsModel_jar_86> getAllComments(){
		ArrayList<CommentsModel_jar_86> allComments = new ArrayList<CommentsModel_jar_86>();
		
		allComments = (ArrayList<CommentsModel_jar_86>) jdbcTemplate.query(SQL_GET_COMMENTS, new CommentsRowMapper_jar_86());
		
		return allComments;
	}
	
}
 