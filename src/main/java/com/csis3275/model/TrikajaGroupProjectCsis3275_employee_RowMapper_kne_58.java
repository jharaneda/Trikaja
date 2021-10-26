package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TrikajaGroupProjectCsis3275_employee_RowMapper_kne_58 implements RowMapper<TrikajaGroupProjectCsis3275_employee_model_kne_58> {

	@Override
	public TrikajaGroupProjectCsis3275_employee_model_kne_58 mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TrikajaGroupProjectCsis3275_employee_model_kne_58 employee = new TrikajaGroupProjectCsis3275_employee_model_kne_58();
		
		employee.setEmployeeID_kne_58(rs.getInt("employeeID"));
		employee.setName_kne_58(rs.getString("name"));
		employee.setEmail_kne_58(rs.getString("email"));
		employee.setPosition_kne_58(rs.getString("position"));
		employee.setNumAssignTicks_kne_58(rs.getInt("numAssignTicks"));
		
		return employee;
	}

}
