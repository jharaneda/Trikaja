package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentsRowMapper_jar_86 implements RowMapper<CommentsModel_jar_86>{

	@Override
	public CommentsModel_jar_86 mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentsModel_jar_86 comments = new CommentsModel_jar_86();
		
		comments.setId(rs.getInt("id"));
		comments.setTicketID(rs.getInt("ticketID"));
		comments.setCreationDate(rs.getString("creationDate"));
		comments.setCreator(rs.getString("creator"));
		comments.setCommentType(rs.getString("commentType"));
		comments.setComment(rs.getString("comment"));
		
		return comments;
	}

}
