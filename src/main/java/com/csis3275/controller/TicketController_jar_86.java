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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csis3275.dao.CommentDAOImpl;
import com.csis3275.dao.TicketDAOImpl;
import com.csis3275.model.CommentsModel_jar_86;
import com.csis3275.model.TicketModel_jar_86;

@Controller
public class TicketController_jar_86 {

	@Autowired
	TicketDAOImpl ticketDAOImpl;
	@Autowired
	CommentDAOImpl commentDAOImpl;

	public TicketModel_jar_86 setupAddForm() {
		return new TicketModel_jar_86();
	}

	// **** END USER show all tickets****
	@RequestMapping("/tickets/all")
	public String showAllTickets(@ModelAttribute("ticket") TicketModel_jar_86 ticket, @ModelAttribute("comments") CommentsModel_jar_86 comment, Model model, HttpSession session) {
		ArrayList<TicketModel_jar_86> allTickets = ticketDAOImpl.getAllTickets();
		model.addAttribute("allTickets", allTickets);

		@SuppressWarnings("unchecked")
		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		// Add in the messages, if the api is blank.
		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
		// Clear the messages before the returning
		session.removeAttribute("messages");

		return "allTickets-jar-86";
	}

	// **** END USER display create ticket form ****
	@GetMapping("/tickets/create")
	public String showCreateTicketForm(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		return "createTicketUser-jar-86";
	}

	// **** END USER get the ticket info and create a new ticket****
	@SuppressWarnings("unchecked")
	@PostMapping("/tickets/create")
	public String createTicket(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		TicketModel_jar_86 newTicket = new TicketModel_jar_86();
		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();

		// Get date and give it a format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		// set each attribute for ticket
		newTicket.setCreationDate(formatter.format(date));
		newTicket.setStatus("Open");
		newTicket.setUserCreator("jharanedac");
		newTicket.setAssigneeUser("Blank");
		newTicket.setTypeOfTicket(createTicket.getTypeOfTicket());
		newTicket.setPriority(createTicket.getPriority());
		newTicket.setPosition(createTicket.getPosition());
		newTicket.setHardwareToBeChanged(createTicket.getHardwareToBeChanged());
		// create new ticket into the DB and get the ticket ID
		Long ticketId = ticketDAOImpl.save(newTicket);

		// set each attribute for comments
		newComment.setTicketID(ticketId);
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator("jharanedac");
		newComment.setCommentType("public");
		newComment.setComment(createComment.getComment());

		commentDAOImpl.createComment(newComment);

		// Populate the message into the session
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		session.setAttribute("messages", messages);

		messages.add("Ticket " + ticketId + " was created");

		return "redirect:/tickets/all";
	}

	// **** END USER display view one ticket****
	@GetMapping("/tickets/viewbyone/{id}")
	public String showOneTicketForm(@PathVariable("id") Long id, @ModelAttribute("comment") CommentsModel_jar_86 newComment, Model model, HttpSession session) {
		TicketModel_jar_86 ticket = ticketDAOImpl.getTicketById(id);
		model.addAttribute("ticketViewed", ticket);

		ArrayList<CommentsModel_jar_86> allComments = commentDAOImpl.getAllComments();
		ArrayList<CommentsModel_jar_86> allCommentsByID = new ArrayList<CommentsModel_jar_86>();

		for (CommentsModel_jar_86 comment : allComments) {
			if (comment.getTicketID() == id) {
				allCommentsByID.add(comment);
			}
		}

		model.addAttribute("commentViewed", allCommentsByID);
		model.addAttribute("comment", newComment);

		return "viewTicketUser-jar-86";
	}

	// **** END USER get the new ticket info and update it****
	@SuppressWarnings("unchecked")
	@PostMapping("/tickets/update")
	public String editTicketUser(@ModelAttribute("ticket") TicketModel_jar_86 ticket, @ModelAttribute("comment") CommentsModel_jar_86 comments, Model model, HttpSession session) {

		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();

		// Get date and give it a format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		// set each attribute for comments
		newComment.setTicketID(ticket.getId());
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator("jharanedac");
		newComment.setCommentType("public");
		newComment.setComment(comments.getComment());

		commentDAOImpl.createComment(newComment);
		ticketDAOImpl.updateTicketUserView(ticket);
		
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		messages.add("Created Ticket " + ticket.getId());
		session.setAttribute("messages", messages);

		return "redirect:/tickets/all";
	}

	// **** MANAGER USER show all tickets****
	@RequestMapping("/manager/tickets/all")
	public String showAllTicketsManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model, HttpSession session) {
		ArrayList<TicketModel_jar_86> allTicketsManager = ticketDAOImpl.getAllTickets();
		model.addAttribute("allTicketsManager", allTicketsManager);

		@SuppressWarnings("unchecked")
		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		// Add in the messages, if the api is blank.
		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
		// Clear the messages before the returning
		session.removeAttribute("messages");

		return "allTicketsManager-jar-86";
	}

	// **** MANAGER USER display view for one ticket****
	@GetMapping("/manager/tickets/viewbyone/{id}")
	public String showOneTicketManagerForm(@PathVariable("id") Long id, @ModelAttribute("comment") CommentsModel_jar_86 newComment, Model model, HttpSession session) {
		TicketModel_jar_86 ticket = ticketDAOImpl.getTicketById(id);
		model.addAttribute("ticketViewed", ticket);

		ArrayList<CommentsModel_jar_86> allComments = commentDAOImpl.getAllComments();
		ArrayList<CommentsModel_jar_86> allCommentsByID = new ArrayList<CommentsModel_jar_86>();

		for (CommentsModel_jar_86 comment : allComments) {
			if (comment.getTicketID() == id) {
				allCommentsByID.add(comment);
			}
		}

		model.addAttribute("commentViewed", allCommentsByID);
		model.addAttribute("comment", newComment);

		return "viewTicketManager-jar-86";
	}

	// **** MANAGER USER display create ticket form****
	@GetMapping("/manager/tickets/create")
	public String showCreateTicketFormManager(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		return "createTicketManager-jar-86";
	}

	// **** MANAGER USER get the ticket info and create a new ticket****
	@SuppressWarnings("unchecked")
	@PostMapping("/manager/tickets/create")
	public String createTicketManager(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		TicketModel_jar_86 newTicket = new TicketModel_jar_86();
		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();

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

		Long ticketId = ticketDAOImpl.save(newTicket);

		// set each attribute for comments
		newComment.setTicketID(ticketId);
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator("jharanedac");
		newComment.setCommentType("public");
		newComment.setComment(createComment.getComment());
		
		commentDAOImpl.createComment(newComment);
		
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		messages.add("Created Ticket " + ticketId);
		session.setAttribute("messages", messages);
		
		return "redirect:/manager/tickets/all";
	}

	// **** MANAGER USER delete ticket by id****
	@SuppressWarnings("unchecked")
	@GetMapping("/manager/tickets/delete/{id}")
	public String deleteStudent(@PathVariable("id") Long id, HttpSession session) {
		// Populate the message into the sesession
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		messages.add("Deleted Ticket " + id);
		session.setAttribute("messages", messages);

		ticketDAOImpl.deleteTicket(id);
		return "redirect:/manager/tickets/all";
	}

	// **** MANAGER USER get the new ticket info and update it****
	@SuppressWarnings("unchecked")
	@PostMapping("/manager/tickets/update")
	public String editTicketManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, @ModelAttribute("comment") CommentsModel_jar_86 comments, Model model, HttpSession session) {
		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		// set each attribute for comments
		newComment.setTicketID(ticket.getId());
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator("jharanedac");
		newComment.setCommentType("public");
		newComment.setComment(comments.getComment());

		commentDAOImpl.createComment(newComment);
		
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		messages.add("Updated Ticket " + ticket.getId());
		session.setAttribute("messages", messages);

		ticketDAOImpl.updateTicket(ticket);
		return "redirect:/manager/tickets/all";
	}
}
