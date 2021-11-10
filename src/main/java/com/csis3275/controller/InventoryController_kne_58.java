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

import com.csis3275.dao.InventoryDAOImp_kne_58;
import com.csis3275.model.InventoryModel_kne_58;

@Controller
public class InventoryController_kne_58 {

	@Autowired
	InventoryDAOImp_kne_58 inventoryDaoImpl;

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
	public String createInventory(
			@ModelAttribute("inventory") InventoryModel_kne_58 createInventory, Model model, HttpSession session) {
		
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
}
