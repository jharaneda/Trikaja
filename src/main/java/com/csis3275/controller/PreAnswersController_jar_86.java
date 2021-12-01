package com.csis3275.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csis3275.dao.PreAnswersDAOImpl_jar_86;
import com.csis3275.dao.SessionDAOImpl_jar_86;

import com.csis3275.model.PredefinedAnswersModel_jar_86;
import com.csis3275.model.SessionModel_jar_86;

@Controller
public class PreAnswersController_jar_86 {
	@Autowired
	PreAnswersDAOImpl_jar_86 preAnswerDAOImpl;
	@Autowired
	SessionDAOImpl_jar_86 sessionDAOImpl;

	public PredefinedAnswersModel_jar_86 setupAddForm() {
		return new PredefinedAnswersModel_jar_86();
	}

	@RequestMapping("/preanswers/list")
	public String showList(@ModelAttribute("answer") PredefinedAnswersModel_jar_86 answer, Model model, HttpSession session) {
		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? messages : new ArrayList<String>();

		SessionModel_jar_86 webSession = new SessionModel_jar_86();
		SessionModel_jar_86 dbSession = new SessionModel_jar_86();

		webSession = (SessionModel_jar_86) session.getAttribute("session") != null ? (SessionModel_jar_86) session.getAttribute("session") : new SessionModel_jar_86();

		ArrayList<PredefinedAnswersModel_jar_86> allAnswers = preAnswerDAOImpl.getAllAnswers();

		if (webSession.getEmail() == null) {
			messages.add("You dont have access to this place. Please Login");
			session.setAttribute("messages", messages);
			return "redirect:/";
		} else if (sessionDAOImpl.getSession(webSession.getId()) != null) {
			dbSession = sessionDAOImpl.getSession(webSession.getId());
			if (webSession.getId().equals(dbSession.getId())) {
				model.addAttribute("allAnswers", allAnswers);
				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "preAnswerList-jar-86";
			}
		}
		return "redirect:/";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/preanswers/delete/{id}")
	public String deleteAnswer(@PathVariable("id") Long id, HttpSession session, Model model) {
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
				messages.add("Deleted Predefined Answer " + id);
				session.setAttribute("messages", messages);

				preAnswerDAOImpl.deleteAnswer(id);
				return "redirect:/preanswers/list";
			}
		}
		return "redirect:/";
	}

	@GetMapping("/preanswers/create")
	public String showCreateAnswerForm(@ModelAttribute("answer") PredefinedAnswersModel_jar_86 answer, Model model, HttpSession session) {
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
				return "createAnswer-jar-86";
			}
		}
		return "redirect:/";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/preanswers/create")
	public String createAnswer(@ModelAttribute("answer") PredefinedAnswersModel_jar_86 answer, Model model, HttpSession session) {

		preAnswerDAOImpl.createAnswer(answer);

		// Populate the message into the session
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		session.setAttribute("messages", messages);

		messages.add("Answer " + answer.getName() + " was created");

		return "redirect:/preanswers/list";
	}

	@GetMapping("/preanswers/viewbyone/{id}")
	public String showOneAnswerForm(@PathVariable("id") Long id, Model model, HttpSession session) {

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
				PredefinedAnswersModel_jar_86 answer = preAnswerDAOImpl.getAnswerById(id);
				model.addAttribute("answerViewed", answer);

				model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());
				// Clear the messages before the returning
				session.removeAttribute("messages");
				return "viewAnswer-jar-86";
			}
		}
		return "redirect:/";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/preanswers/update")
	public String editAnswer(@ModelAttribute("answer") PredefinedAnswersModel_jar_86 answer, Model model, HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages") : new ArrayList<String>();
		messages.add("Updated Answer " + answer.getName());
		session.setAttribute("messages", messages);

		preAnswerDAOImpl.updateAnswer(answer);
		return "redirect:/preanswers/list";
	}
}
