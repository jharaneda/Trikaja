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

		return "managerPageCreate_employee_kne_58";
	}

	@PostMapping("/manager/employee/create")
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
	
	@GetMapping("/manager/employee/delete/")
	public String deleteEmployee(@RequestParam(required = true) int employeeID, Model model, HttpSession session) {
		employeeDaoImpl.deleteEmployee_kne_58(employeeID);
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		messages.add("Deleted Employee " + employeeID);
		session.setAttribute("messages", messages);

		return "redirect:/manager/";
	}

	@GetMapping("/manager/employee/edit")
	public String editEmployee(@RequestParam(required = true) int employeeID, Model model) {
		TrikajaGroupProjectCsis3275_employee_model_kne_58 upEmployee = employeeDaoImpl
				.findEmployeeByID_kne_58(employeeID);
		model.addAttribute("employee", upEmployee);
		return "managerPageEdit_employee_kne_58";
	}

	@PostMapping("/manager/employee/edit")
	public String updateEmployee(
			@ModelAttribute("employee") TrikajaGroupProjectCsis3275_employee_model_kne_58 upEmployee, Model model,
			HttpSession session) {
		
		ArrayList<String> messages = new ArrayList<String>();
		
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		
		messages.add("Successfully Updated Employee " + upEmployee.getName());
		session.setAttribute("messages", messages);
		
		employeeDaoImpl.updateEmployee_kne_58(upEmployee);
		
		List<TrikajaGroupProjectCsis3275_employee_model_kne_58> employees = employeeDaoImpl.getEmployees_kne_58();
		model.addAttribute("employees",employees);
		model.addAttribute("message", "Successfully Edited Employee" + upEmployee.getEmployeeID());
		
		return "redirect:/manager";
	}
	
	@RequestMapping("/manager/user/create")
	public String showCreateUser_kne_58(
			@ModelAttribute("user") TrikajaGroupProjectCsis3275_user_model_kne_58 createUser, Model model,
			HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> usersArray = userDaoImpl.getUsers_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("usersArray", usersArray);

		session.removeAttribute("messages");

		return "managerPageCreate_user_kne_58";
	}

	@PostMapping("/manager/user/create")
	public String createUser(@ModelAttribute("user") TrikajaGroupProjectCsis3275_user_model_kne_58 createUser,
			Model model, HttpSession session) {

		userDaoImpl.createUser_kne_58(createUser);

		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();

		messages.add("Created User " + createUser.getName());

		session.setAttribute("messages", messages);

		return "redirect:/manager";

	}
	
	@GetMapping("/manager/user/delete/")
	public String deleteUser(@RequestParam(required = true) int userID, Model model, HttpSession session) {
		userDaoImpl.deleteUser_kne_58(userID);
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		messages.add("Deleted User " + userID);
		session.setAttribute("messages", messages);

		return "redirect:/manager/";
	}
	
	@GetMapping("/manager/user/edit")
	public String editUser(@RequestParam(required = true) int userID, Model model) {
		TrikajaGroupProjectCsis3275_user_model_kne_58 upUser= userDaoImpl
				.findUserByID_kne_58(userID);
		model.addAttribute("user", upUser);
		return "managerPageEdit_user_kne_58";
	}

	@PostMapping("/manager/user/edit")
	public String updateUser(
			@ModelAttribute("user") TrikajaGroupProjectCsis3275_user_model_kne_58 upUser, Model model,
			HttpSession session) {
		
		ArrayList<String> messages = new ArrayList<String>();
		
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		
		messages.add("Successfully Updated User " + upUser.getName());
		session.setAttribute("messages", messages);
		
		userDaoImpl.updateUser_kne_58(upUser);
		
		List<TrikajaGroupProjectCsis3275_user_model_kne_58> users = userDaoImpl.getUsers_kne_58();
		model.addAttribute("users",users);
		model.addAttribute("message", "Successfully Edited User" + upUser.getUserID());
		
		return "redirect:/manager";
	}
	
	
	
	@RequestMapping("/agent")
	public String agentShowUsers_kne_58(
			@ModelAttribute("user")
			TrikajaGroupProjectCsis3275_user_model_kne_58 createUser, Model model, HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> usersArray = userDaoImpl.getUsers_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());


		model.addAttribute("usersArray", usersArray);

		session.removeAttribute("messages");

		return "agentPage_kne_58";
	}
	
	@RequestMapping("/agent/user/create")
	public String agentShowCreateUser_kne_58(
			@ModelAttribute("user") TrikajaGroupProjectCsis3275_user_model_kne_58 createUser, Model model,
			HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> usersArray = userDaoImpl.getUsers_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("usersArray", usersArray);

		session.removeAttribute("messages");

		return "agentPageCreate_user_kne_58";
	}

	@PostMapping("/agent/user/create")
	public String agentCreateUser(@ModelAttribute("user") TrikajaGroupProjectCsis3275_user_model_kne_58 createUser,
			Model model, HttpSession session) {

		userDaoImpl.createUser_kne_58(createUser);

		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();

		messages.add("Created User " + createUser.getName());

		session.setAttribute("messages", messages);

		return "redirect:/agent";

	}
	
	@GetMapping("/agent/user/edit")
	public String agentEditUser(@RequestParam(required = true) int userID, Model model) {
		TrikajaGroupProjectCsis3275_user_model_kne_58 upUser= userDaoImpl
				.findUserByID_kne_58(userID);
		model.addAttribute("user", upUser);
		return "agentPageEdit_user_kne_58";
	}

	@PostMapping("/agent/user/edit")
	public String agentUpdateUser(
			@ModelAttribute("user") TrikajaGroupProjectCsis3275_user_model_kne_58 upUser, Model model,
			HttpSession session) {
		
		ArrayList<String> messages = new ArrayList<String>();
		
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		
		messages.add("Successfully Updated User " + upUser.getName());
		session.setAttribute("messages", messages);
		
		userDaoImpl.updateUser_kne_58(upUser);
		
		List<TrikajaGroupProjectCsis3275_user_model_kne_58> users = userDaoImpl.getUsers_kne_58();
		model.addAttribute("users",users);
		model.addAttribute("message", "Successfully Edited User" + upUser.getUserID());
		
		return "redirect:/agent";
	}
	
	@GetMapping("/agent/user/delete/")
	public String agentDeleteUser(@RequestParam(required = true) int userID, Model model, HttpSession session) {
		userDaoImpl.deleteUser_kne_58(userID);
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		messages.add("Deleted User " + userID);
		session.setAttribute("messages", messages);

		return "redirect:/agent/";
	}
	
	@RequestMapping("/user")
	public String showUser_kne_58(
			@ModelAttribute("user")
			TrikajaGroupProjectCsis3275_user_model_kne_58 createUser, Model model, HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_user_model_kne_58> usersArray = userDaoImpl.getUsers_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());


		model.addAttribute("usersArray", usersArray);

		session.removeAttribute("messages");

		return "userPage_kne_58";
	}



}
