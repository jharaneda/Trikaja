package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.TrikajaGroupProjectCsis3275_employee_model_kne_58;
import com.csis3275.model.TrikajaGroupProjectCsis3275_employee_RowMapper_kne_58;

@Service
public class EmployeeDAOImpl_kne_58 {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL_THE_EMPLOYEES = "SELECT * FROM employee";
	private final String SQL_CREATE_EMPLOYEE = "INSERT INTO employee (name, email, position, numAssignTicks) VALUES (?,?,?,?)";
	private final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE employeeID = ?";
	private final String SQL_UPDATE_EMPLOYEE = "UPDATE employee set name = ?, email = ?, position = ?, numAssignTicks = ? WHERE employeeID = ?";
	private final String SQL_FIND_EMPLOYEE = "SELECT * FROM employee WHERE employeeID = ?";

	@Autowired
	public EmployeeDAOImpl_kne_58(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> getEmployees_kne_58() {

		ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> employees = new ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58>();

		employees = (ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58>) jdbcTemplate
				.query(SQL_GET_ALL_THE_EMPLOYEES, new TrikajaGroupProjectCsis3275_employee_RowMapper_kne_58());

		return employees;
	}

	public boolean createEmployee_kne_58(TrikajaGroupProjectCsis3275_employee_model_kne_58 newEmployee) {

		return jdbcTemplate.update(SQL_CREATE_EMPLOYEE, newEmployee.getName(), newEmployee.getEmail(),
				newEmployee.getPosition(), newEmployee.getNumAssignTicks()) > 0;
	}

	public boolean deleteEmployee_kne_58(int employeeID) {
		return jdbcTemplate.update(SQL_DELETE_EMPLOYEE, employeeID) > 0;

	}

	public boolean updateEmployee_kne_58(TrikajaGroupProjectCsis3275_employee_model_kne_58 employee) {
		return jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, employee.getName(), employee.getEmail(), employee.getPosition(),
				employee.getNumAssignTicks(),employee.getEmployeeID()) > 0;

	}

	@SuppressWarnings("deprecation")
	public TrikajaGroupProjectCsis3275_employee_model_kne_58 findEmployeeByID_kne_58(int employeeID) {
		return jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE, new Object[] { employeeID }, new TrikajaGroupProjectCsis3275_employee_RowMapper_kne_58());
	}
}
