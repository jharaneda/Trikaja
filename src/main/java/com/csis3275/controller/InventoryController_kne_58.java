package com.csis3275.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.dao.InventoryDAOImp_kne_58;
import com.csis3275.dao.TicketDAOImpl;
import com.csis3275.model.InventoryModel_kne_58;
import com.csis3275.model.TicketModel_jar_86;
import com.csis3275.model.TrikajaGroupProjectCsis3275_employee_model_kne_58;

@Controller
public class InventoryController_kne_58 {

	@Autowired
	InventoryDAOImp_kne_58 inventoryDaoImpl;

	@Autowired
	TicketDAOImpl ticketDAOImpl;

	@RequestMapping("/inventory")
	public String showInventory_kne_58(@ModelAttribute("inventory") InventoryModel_kne_58 createInventory, Model model,
			HttpSession session) {

		ArrayList<InventoryModel_kne_58> inventoryArray = inventoryDaoImpl.getInventory_kne_58();

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");

		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("inventoryArray", inventoryArray);
		session.removeAttribute("messages");

		return "inventoryView-kne-58";
	}

	@PostMapping("/inventory/create")
	public String createInventory(@ModelAttribute("inventory") InventoryModel_kne_58 createInventory, Model model,
			HttpSession session) {

		createInventory.setItemLocation("Ordered");
		createInventory.setStatus("Working");
		inventoryDaoImpl.createInventory_kne_58(createInventory);

		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();

		messages.add("Created Item: " + createInventory.getItemType());

		session.setAttribute("messages", messages);

		return "redirect:/inventory";
	}

	@RequestMapping("/inventory/assign")
	public String viewHardwareTickets(@ModelAttribute("ticket") TicketModel_jar_86 ticket, Model model,
			HttpSession session) {

		ArrayList<TicketModel_jar_86> ticketArray = ticketDAOImpl.getOpenHardwareTickets("Open", "Hardware");

		ArrayList<String> messages = (ArrayList<String>) session.getAttribute("messages");
		model.addAttribute("messages", messages != null ? messages : new ArrayList<String>());

		model.addAttribute("ticketArray", ticketArray);
		session.removeAttribute("messages");

		return "hardwareAssignPage-kne-58";
	}
	
	@GetMapping("/inventory/assign/assignTo/{ticketID}/{hardware}")
	public String viewHardwareTickets2(@PathVariable int ticketID,@PathVariable String hardware, Model model,HttpSession session) {
		
		if(inventoryDaoImpl.findInventoryByAssigned(hardware) == null) {
			ArrayList<String> messages = new ArrayList<String>();

			messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
					: new ArrayList<String>();

			messages.add("Need to order more inventory");
			session.setAttribute("messages", messages);
			return "redirect:/inventory";
		}else {
			InventoryModel_kne_58 upInventory = inventoryDaoImpl.findInventoryByAssigned(hardware);
			upInventory.setAssignedTo(ticketID);
			model.addAttribute("inventory", upInventory);
			return "assignConfirmPage-kne-58";
		}
		
	}
	
	
	@PostMapping("/inventory/assign/assignTo")
	public String viewHardwareTickets2(@ModelAttribute("inventory") InventoryModel_kne_58 upInventory, Model model,
	HttpSession session) {


		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();

		messages.add("Successfully Updated Item " + upInventory.getItemID());
		session.setAttribute("messages", messages);

		inventoryDaoImpl.updateInventoryAssigned(upInventory);

		List<InventoryModel_kne_58> inventoryModel_kne_58s = inventoryDaoImpl.getInventory_kne_58();
		model.addAttribute("inventory", upInventory);
		model.addAttribute("message", "Successfully Edited Item" + upInventory.getItemID());

	

		return "redirect:/inventory";
	}

	@GetMapping("/inventory/edit")
	public String editInventory(@RequestParam(required = true) int itemID, Model model) {

		InventoryModel_kne_58 upInventory = inventoryDaoImpl.findInventoryByID(itemID);
		model.addAttribute("inventory", upInventory);

		return "IndividHardwareAssignPage-kne-58";
	}

	@PostMapping("/inventory/edit")
	public String updateInventory(@ModelAttribute("inventory") InventoryModel_kne_58 upInventory, Model model,
			HttpSession session) {

		ArrayList<String> messages = new ArrayList<String>();

		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();

		messages.add("Successfully Updated Item " + upInventory.getItemID());
		session.setAttribute("messages", messages);

		inventoryDaoImpl.updateInventory(upInventory);

		List<InventoryModel_kne_58> inventoryModel_kne_58s = inventoryDaoImpl.getInventory_kne_58();
		model.addAttribute("inventory", upInventory);
		model.addAttribute("message", "Successfully Edited Item" + upInventory.getItemID());

		return "redirect:/inventory";
	}
	
	@GetMapping("inventory/delete/")
	public String deleteInventory(@RequestParam(required = true) int itemID, Model model, HttpSession session) {
		inventoryDaoImpl.deleteInventory(itemID);
		ArrayList<String> messages = new ArrayList<String>();
		messages = session.getAttribute("messages") != null ? (ArrayList<String>) session.getAttribute("messages")
				: new ArrayList<String>();
		messages.add("Deleted Item " + itemID);
		session.setAttribute("messages", messages);

		return "redirect:/inventory";
	}
}
