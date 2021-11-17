package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.TrikajaGroupProjectCsis3275_user_RowMapper_kne_58;
import com.csis3275.model.TrikajaGroupProjectCsis3275_user_model_kne_58;

@Service
public class UserDAOImpl_kne_58 {

	JdbcTemplate jdbcTemplate2;

	private final String SQL_GET_ALL_THE_USERS = "SELECT * FROM users";
	private final String SQL_CREATE_USER = "INSERT INTO users (name, email, numTickets) VALUES (?,?,?)";
	private final String SQL_DELETE_USER = "DELETE FROM users WHERE userID = ?";
	private final String SQL_UPDATE_USER = "UPDATE users SET name = ?, email = ?, numTickets = ? WHERE userID = ?";
	private final String SQL_FIND_USER = "SELECT * FROM users WHERE userID = ?";
	private final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

	@Autowired
	public UserDAOImpl_kne_58(DataSource dataSource2) {
		jdbcTemplate2 = new JdbcTemplate(dataSource2);
	}

	public ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> getUsers_kne_58() {

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> users = new ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58>();

		users = (ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58>) jdbcTemplate2.query(SQL_GET_ALL_THE_USERS,
				new TrikajaGroupProjectCsis3275_user_RowMapper_kne_58());

		return users;
	}

	public boolean createUser_kne_58(TrikajaGroupProjectCsis3275_user_model_kne_58 newUser) {

		return jdbcTemplate2.update(SQL_CREATE_USER, newUser.getName(), newUser.getEmail(),
				newUser.getNumTickets()) > 0;
	}

	public boolean deleteUser_kne_58(int userID) {
		return jdbcTemplate2.update(SQL_DELETE_USER, userID) > 0;

	}

	public boolean updateUser_kne_58(TrikajaGroupProjectCsis3275_user_model_kne_58 user) {
		return jdbcTemplate2.update(SQL_UPDATE_USER, user.getName(), user.getEmail(),
				user.getNumTickets(),user.getUserID()) > 0;

	}
	
	@SuppressWarnings("deprecation")
	public TrikajaGroupProjectCsis3275_user_model_kne_58 findUserByID_kne_58(int userID) {
		return jdbcTemplate2.queryForObject(SQL_FIND_USER, new Object[] { userID }, new TrikajaGroupProjectCsis3275_user_RowMapper_kne_58());
	}
	
	@SuppressWarnings("deprecation")
	public TrikajaGroupProjectCsis3275_user_model_kne_58 findUserByEmail_kne_58(String email) {
		try {
			return jdbcTemplate2.queryForObject(SQL_FIND_USER_BY_EMAIL, new Object[] { email }, new TrikajaGroupProjectCsis3275_user_RowMapper_kne_58());
			
		} catch (Exception e) {
			return null;
		}
	}
}

