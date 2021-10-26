package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TrikajaGroupProjectCsis3275_user_RowMapper_kne_58 implements RowMapper<TrikajaGroupProjectCsis3275_user_model_kne_58>{

	@Override
	public TrikajaGroupProjectCsis3275_user_model_kne_58 mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TrikajaGroupProjectCsis3275_user_model_kne_58 user = new TrikajaGroupProjectCsis3275_user_model_kne_58();
		
		user.setUserID_kne_58(rs.getInt("userID"));
		user.setName_kne_58(rs.getString("name"));
		user.setEmail_kne_58(rs.getString("email"));
		user.setNumTickets_kne_58(rs.getInt("numTickets"));
		
		return user;
	}

}
