package com.csis3275.DAO;

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

	private final String GET_ALL_THE_EMPLOYEES = "SELECT * FROM employee";
	private final String CREATE_EMPLOYEE = "INSERT INTO employee (name,email,position,numAssignTicks) VALUES (?,?,?,?)";
	private final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE employeeID = ?";
	private final String UPDATE_EMPLOYEE = "UPDATE employee SET name = ?, email = ?, position = ?, numAssignTicks = ? WHERE employeeID = ?";
	private final String FIND_EMPLOYEE = "SELECT * FROM employee WHERE employeeID = ?";

	@Autowired
	public EmployeeDAOImpl_kne_58(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> getEmployees() {

		ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> employees = new ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58>();

		employees = (ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58>) jdbcTemplate
				.query(GET_ALL_THE_EMPLOYEES, new TrikajaGroupProjectCsis3275_employee_RowMapper_kne_58());

		return employees;
	}

	public boolean createEmployee(TrikajaGroupProjectCsis3275_employee_model_kne_58 newEmployee) {

		return jdbcTemplate.update(CREATE_EMPLOYEE, newEmployee.getName(), newEmployee.getEmail(),
				newEmployee.getPosition(), newEmployee.getNumAssignTicks()) > 0;
	}

	public boolean deleteEmployee(int employeeID) {
		return jdbcTemplate.update(DELETE_EMPLOYEE, employeeID) > 0;

	}

	public boolean updateEmployee(TrikajaGroupProjectCsis3275_employee_model_kne_58 employee) {
		return jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getName(), employee.getEmail(), employee.getPosition(),
				employee.getNumAssignTicks()) > 0;

	}

	@SuppressWarnings("deprecation")
	public TrikajaGroupProjectCsis3275_employee_model_kne_58 findEmployeeByID(int employeeID) {
		return jdbcTemplate.queryForObject(FIND_EMPLOYEE, new Object[] { employeeID }, new TrikajaGroupProjectCsis3275_employee_RowMapper_kne_58());
	}
}
