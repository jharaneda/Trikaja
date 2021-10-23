package com.csis3275.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csis3275.dao.TicketHardwareDAOImpl;
import com.csis3275.model.TicketHardwareModel_jar_86;

@Controller
public class TicketHardwareController_jar_86 {
	
	@Autowired
	TicketHardwareDAOImpl ticketHardwareDAOImpl;
	
	public TicketHardwareModel_jar_86 setupAddForm() {
		return new TicketHardwareModel_jar_86();
	}
	
	@RequestMapping("/tickets/all")
	public String showAllTickets(@ModelAttribute("ticket") TicketHardwareModel_jar_86 ticketHardware, Model model, HttpSession session) {
		ArrayList<TicketHardwareModel_jar_86> allHardwareTickets = ticketHardwareDAOImpl.getAllTickets();
		
		model.addAttribute("allHardwareTickets", allHardwareTickets);
		
		return "allHardwareTickets_jar_86";
	}
}
