package com.csis3275.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csis3275.dao.TicketDAOImpl;
import com.csis3275.model.TicketModel_jar_86;

@Controller
public class TicketController_jar_86 {
	
	@Autowired
	TicketDAOImpl ticketDAOImpl;
	
	public TicketModel_jar_86 setupAddForm() {
		return new TicketModel_jar_86();
	}
	
	@RequestMapping("/tickets/all")
	public String showAllTickets(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model, HttpSession session) {
		ArrayList<TicketModel_jar_86> allTickets = ticketDAOImpl.getAllTickets();
		
		model.addAttribute("allTickets", allTickets);
		
		return "allTickets-jar-86";
	}
	
	@PostMapping("/tickets/create")
	public String createTicket(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, Model model, HttpSession session) {
		TicketModel_jar_86 newTicket = new TicketModel_jar_86();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		newTicket.setCreationDate(formatter.format(date));
		newTicket.setStatus("Open");
		newTicket.setUserCreator("jharanedac");
		newTicket.setAssigneeUser("Blank");
		newTicket.setTypeOfTicket(createTicket.getTypeOfTicket());
		newTicket.setPriority(createTicket.getPriority());
		newTicket.setPosition(createTicket.getPosition());
		newTicket.setHardwareToBeChanged(createTicket.getHardwareToBeChanged());
		newTicket.setComments(createTicket.getComments());
		ticketDAOImpl.createTicket(newTicket);
		
		return "redirect:/tickets/all";
	}
	
	@GetMapping("/tickets/create")
	public String showCreateTicketForm(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, Model model, HttpSession session) {
		return "createTicketUser-jar-86";
	}
	
	@GetMapping("/tickets/viewbyone")
	public String showTicketForm(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, Model model, HttpSession session) {
		return "createTicketUser-jar-86";
	}
}
