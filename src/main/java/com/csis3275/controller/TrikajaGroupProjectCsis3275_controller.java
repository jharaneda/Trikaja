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
import com.csis3275.model.TrikajaGroupProjectCsis3275_employee_model_kne_58;

@Controller
public class TrikajaGroupProjectCsis3275_controller {

	@Autowired
	EmployeeDAOImpl_kne_58 employeeDaoImpl;

	public TrikajaGroupProjectCsis3275_employee_model_kne_58 addforum() {
		return new TrikajaGroupProjectCsis3275_employee_model_kne_58();
	}

	@RequestMapping("/")
	public String showEmployees(
			@ModelAttribute("employee") TrikajaGroupProjectCsis3275_employee_model_kne_58 createEmployee, Model model,
			HttpSession session) {

		ArrayList<TrikajaGroupProjectCsis3275_employee_model_kne_58> employeesArray = employeeDaoImpl.getEmployees();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("employeesArray", employeesArray);
		
		session.removeAttribute("messages");

		return "managerPage_kne_58";
	}

}
