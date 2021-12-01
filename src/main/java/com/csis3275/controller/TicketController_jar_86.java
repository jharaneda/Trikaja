package com.csis3275.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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
import com.csis3275.dao.EmployeeDAOImpl_kne_58;
import com.csis3275.dao.PreAnswersDAOImpl_jar_86;
import com.csis3275.dao.SessionDAOImpl_jar_86;
import com.csis3275.dao.TicketDAOImpl;
import com.csis3275.dao.UserDAOImpl_kne_58;
import com.csis3275.model.CommentsModel_jar_86;
import com.csis3275.model.PredefinedAnswersModel_jar_86;
import com.csis3275.model.SessionModel_jar_86;
import com.csis3275.model.TicketModel_jar_86;
import com.csis3275.model.TrikajaGroupProjectCsis3275_employee_model_kne_58;
import com.csis3275.model.TrikajaGroupProjectCsis3275_user_model_kne_58;

@Controller
public class TicketController_jar_86 {

	@Autowired
	TicketDAOImpl ticketDAOImpl;
	@Autowired
	CommentDAOImpl commentDAOImpl;

	@Autowired
	EmployeeDAOImpl_kne_58 emplDAOImpl;
	@Autowired
	UserDAOImpl_kne_58 userDAOImpl;
	@Autowired
	SessionDAOImpl_jar_86 sessionDAOImpl;
	@Autowired
	PreAnswersDAOImpl_jar_86 preAnswerDAOImpl;

	public TicketModel_jar_86 setupAddForm() {
		return new TicketModel_jar_86();
	}

	@RequestMapping("/")
	public String showHome(@ModelAttribute("session") TrikajaGroupProjectCsis3275_employee_model_kne_58 userSession, Model model, HttpSession session) {

		model.addAttribute("session", userSession);

		@SuppressWarnings("unchecked")
		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		// Add in the messages, if the api is blank.
		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
		// Clear the messages before the returning
		session.removeAttribute("messages");
		session.removeAttribute("session");

		return "login-jar-86";

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	public String login(@ModelAttribute("session") TrikajaGroupProjectCsis3275_employee_model_kne_58 userSession, Model model, HttpSession session) {
		String email = userSession.getEmail();
		String password = Base64.getEncoder().encodeToString(userSession.getPassword().getBytes());

		TrikajaGroupProjectCsis3275_employee_model_kne_58 employeePass = emplDAOImpl.findEmployeeByEmail_kne_58(email);
		TrikajaGroupProjectCsis3275_user_model_kne_58 userPass = userDAOImpl.findUserByEmail_kne_58(email);

		SessionModel_jar_86 uSession = new SessionModel_jar_86();

		if (employeePass != null) {
			if (employeePass.getPassword().equals(password)) {
				switch (employeePass.getPosition()) {
				case "Manager":
					// set attribute of session model
					uSession.setId(session.getId());
					uSession.setEmail(employeePass.getEmail());
					uSession.setPosition(employeePass.getPosition());

					// create session into DB
					sessionDAOImpl.createSession(uSession);
					// add session object into session
					session.setAttribute("session", uSession);
					return "redirect:/manager/tickets/all";
				case "Agent":
					// set attribute of session model
					uSession.setId(session.getId());
					uSession.setEmail(employeePass.getEmail());
					uSession.setPosition(employeePass.getPosition());

					// create session into DB
					sessionDAOImpl.createSession(uSession);
					// add session object into session
					session.setAttribute("session", uSession);
					return "redirect:/manager/tickets/all";
				}
			}
		} else if (userPass != null) {
			if (userPass.getPassword().equals(password)) {
				// set attribute of session model
				uSession.setId(session.getId());
				uSession.setEmail(userPass.getEmail());
				uSession.setPosition("user");
				// create session into DB
				sessionDAOImpl.createSession(uSession);
				// add session object into session
				session.setAttribute("session", uSession);
				return "redirect:/tickets/all";
			}
		}

		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		messages.add("Holy moly! Your credentials are wrong. Please try again");
		session.setAttribute("messages", messages);
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(@ModelAttribute("session") TrikajaGroupProjectCsis3275_employee_model_kne_58 userSession, Model model, HttpSession session) {
		session.removeAttribute("session");
		session.invalidate();

		return "redirect:/";
	}

	// **** END USER show all tickets****
	@RequestMapping("/tickets/all")
	public String showAllTickets(@ModelAttribute("ticket") TicketModel_jar_86 ticket, @ModelAttribute("comments") CommentsModel_jar_86 comment, Model model, HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();
		String[] user = webSession.getEmail().split("@");
		ArrayList<TicketModel_jar_86> allTickets = ticketDAOImpl.getTicketByUser(user[0]);

		//
		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {
				model.addAttribute("allTickets", allTickets);
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "allTickets-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** END USER display create ticket form ****
	@GetMapping("/tickets/create")
	public String showCreateTicketForm(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "createTicketUser-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** END USER get the ticket info and create a new ticket****
	@SuppressWarnings("unchecked")
	@PostMapping("/tickets/create")
	public String createTicket(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		TicketModel_jar_86 newTicket = new TicketModel_jar_86();
		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();
		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		// Get date and give it a format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String[] user = webSession.getEmail().split("@");

		// set each attribute for ticket
		newTicket.setCreationDate(formatter.format(date));
		newTicket.setStatus("Open");
		newTicket.setUserCreator(user[0]);
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
		newComment.setCreator(user[0]);
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

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {
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
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "viewTicketUser-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** END USER get the new ticket info and update it****
	@SuppressWarnings("unchecked")
	@PostMapping("/tickets/update")
	public String editTicketUser(@ModelAttribute("ticket") TicketModel_jar_86 ticket, @ModelAttribute("comment") CommentsModel_jar_86 comments, Model model, HttpSession session) {

		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();
		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();
		String[] user = webSession.getEmail().split("@");

		// Get date and give it a format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		// set each attribute for comments
		newComment.setTicketID(ticket.getId());
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator(user[0]);
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
	@SuppressWarnings("unchecked")
	@RequestMapping("/manager/tickets/all")
	public String showAllTicketsManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model, HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				ArrayList<TicketModel_jar_86> allTicketsManager = ticketDAOImpl.getAllTickets();
				model.addAttribute("allTicketsManager", allTicketsManager);

				messages = (ArrayList<String>) session.getAttribute("messages");

				// Add in the messages, if the api is blank.
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "allTicketsManager-jar-86";
			}
		}
		return "redirect:/";

	}

	// **** MANAGER USER show all tickets****
	@SuppressWarnings("unchecked")
	@RequestMapping("/manager/tickets/open")
	public String showAllOpenTicketsManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model, HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				ArrayList<TicketModel_jar_86> allTicketsManager = ticketDAOImpl.getAllOpenTickets();
				model.addAttribute("allOpenTicketsManager", allTicketsManager);

				messages = (ArrayList<String>) session.getAttribute("messages");

				// Add in the messages, if the api is blank.
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "allTicketOpenManager-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** MANAGER USER show all tickets****
	@SuppressWarnings("unchecked")
	@RequestMapping("/manager/tickets/pending")
	public String showAllPendingTicketsManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model, HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				ArrayList<TicketModel_jar_86> allTicketsManager = ticketDAOImpl.getAllPendingTickets();
				model.addAttribute("allPendingTicketsManager", allTicketsManager);

				messages = (ArrayList<String>) session.getAttribute("messages");

				// Add in the messages, if the api is blank.
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "allTicketPendingManager-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** MANAGER USER show all tickets****
	@SuppressWarnings("unchecked")
	@RequestMapping("/manager/tickets/solved")
	public String showAllSolvedTicketsManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model, HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				ArrayList<TicketModel_jar_86> allTicketsManager = ticketDAOImpl.getAllSolvedTickets();
				model.addAttribute("allSolvedTicketsManager", allTicketsManager);

				messages = (ArrayList<String>) session.getAttribute("messages");

				// Add in the messages, if the api is blank.
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "allTicketSolvedManager-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** MANAGER USER display view for one ticket****
	@GetMapping("/manager/tickets/viewbyone/{id}")
	public String showOneTicketManagerForm(@PathVariable("id") Long id, @ModelAttribute("comment") CommentsModel_jar_86 newComment, Model model, HttpSession session) {
		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				TicketModel_jar_86 ticket = ticketDAOImpl.getTicketById(id);
				model.addAttribute("ticketViewed", ticket);

				ArrayList<CommentsModel_jar_86> allComments = commentDAOImpl.getAllComments();
				ArrayList<CommentsModel_jar_86> allCommentsByID = new ArrayList<CommentsModel_jar_86>();

				for (CommentsModel_jar_86 comment : allComments) {
					if (comment.getTicketID() == id) {
						allCommentsByID.add(comment);
					}
				}

				ArrayList<PredefinedAnswersModel_jar_86> answers = preAnswerDAOImpl.getAllAnswers();

				model.addAttribute("commentViewed", allCommentsByID);
				model.addAttribute("comment", newComment);
				// Add in the messages, if the api is blank.
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				model.addAttribute("predefinedAnswers", answers);
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "viewTicketManager-jar-86";
			}
		}

		return "redirect:/";
	}

	// **** MANAGER USER display create ticket form****
	@GetMapping("/manager/tickets/create")
	public String showCreateTicketFormManager(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "createTicketManager-jar-86";
			}
		}
		return "redirect:/";
	}

	// **** MANAGER USER get the ticket info and create a new ticket****
	@SuppressWarnings("unchecked")
	@PostMapping("/manager/tickets/create")
	public String createTicketManager(@ModelAttribute("ticket") TicketModel_jar_86 createTicket, @ModelAttribute("comment") CommentsModel_jar_86 createComment, Model model, HttpSession session) {
		TicketModel_jar_86 newTicket = new TicketModel_jar_86();
		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();
		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		String[] user = webSession.getEmail().split("@");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		newTicket.setCreationDate(formatter.format(date));
		newTicket.setStatus("Open");
		newTicket.setUserCreator(user[0]);
		newTicket.setAssigneeUser("Blank");
		newTicket.setTypeOfTicket(createTicket.getTypeOfTicket());
		newTicket.setPriority(createTicket.getPriority());
		newTicket.setPosition(createTicket.getPosition());
		newTicket.setHardwareToBeChanged(createTicket.getHardwareToBeChanged());

		Long ticketId = ticketDAOImpl.save(newTicket);

		// set each attribute for comments
		newComment.setTicketID(ticketId);
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator(user[0]);
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
	public String deleteTicket(@PathVariable("id") Long id, HttpSession session, Model model) {
		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {

				messages = new ArrayList<String>();
				messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
				messages.add("Deleted Ticket " + id);
				session.setAttribute("messages", messages);

				ticketDAOImpl.deleteTicket(id);
				return "redirect:/manager/tickets/all";
			}
		}
		return "redirect:/";
	}

	// **** MANAGER USER get the new ticket info and update it****
	@SuppressWarnings("unchecked")
	@PostMapping("/manager/tickets/update")
	public String editTicketManager(@ModelAttribute("ticket") TicketModel_jar_86 ticket, @ModelAttribute("comment") CommentsModel_jar_86 comments, Model model, HttpSession session) {
		CommentsModel_jar_86 newComment = new CommentsModel_jar_86();
		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		String[] user = webSession.getEmail().split("@");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		// set each attribute for comments
		newComment.setTicketID(ticket.getId());
		newComment.setCreationDate(formatter.format(date));
		newComment.setCreator(user[0]);
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
