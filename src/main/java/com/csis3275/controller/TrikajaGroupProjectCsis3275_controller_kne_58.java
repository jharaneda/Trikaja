package com.csis3275.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.DAO.EmployeeDAOImpl_kne_58;
import com.csis3275.DAO.UserDAOImpl_kne_58;
import com.csis3275.model.TrikajaGroupProjectCsis3275_employee_model_kne_58;
import com.csis3275.model.TrikajaGroupProjectCsis3275_user_model_kne_58;

@Controller
public class TrikajaGroupProjectCsis3275_controller_kne_58 {

	@Autowired
	EmployeeDAOImpl_kne_58 employeeDaoImpl;

	@Autowired
	UserDAOImpl_kne_58 userDaoImpl;

	public TrikajaGroupProjectCsis3275_employee_model_kne_58 addforum() {
		return new TrikajaGroupProjectCsis3275_employee_model_kne_58();
	}

	@RequestMapping("/manager")
	public String showEmployees_kne_58(
			@ModelAttribute("employee") TrikajaGroupProjectCsis3275_employee_model_kne_58 createEmployee,
			TrikajaGroupProjectCsis3275_user_model_kne_58 createUser, Model model, HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> employeesArray = employeeDaoImpl
				.getEmployees_kne_58();

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> usersArray = userDaoImpl.getUsers_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("employeesArray", employeesArray);

		model.addAttribute("usersArray", usersArray);

		session.removeAttribute("messages");

		return "managerPage_kne_58";
	}
	
	@RequestMapping("/manager/employee/create")
	public String showCreateEmploy_kne_58(
			@ModelAttribute("employee") TrikajaGroupProjectCsis3275_employee_model_kne_58 createEmployee,
			TrikajaGroupProjectCsis3275_user_model_kne_58 createUser, Model model, HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> employeesArray = employeeDaoImpl
				.getEmployees_kne_58();

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> usersArray = userDaoImpl.getUsers_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("employeesArray", employeesArray);

		model.addAttribute("usersArray", usersArray);

		session.removeAttribute("messages");

		return "managerPageCreate_kne_58";
	}

	@PostMapping("manager/employee/create")
	public String createEmployee(
			@ModelAttribute("employee") TrikajaGroupProjectCsis3275_employee_model_kne_58 createEmployee, Model model,
			HttpSession session) {
		
		employeeDaoImpl.createEmployee_kne_58(createEmployee);
		
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		
		messages.add("Created Employee " + createEmployee.getName());
		
		session.setAttribute("messages", messages);
		
				return "redirect:/manager";

	}

}
