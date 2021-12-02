package com.csis3275.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.csis3275.controller.InventoryController_kne_58;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController_kne_58.class)
class InventoryControllerTest {

	@Autowired
	private MockMvc mvc;
	
	
	//Testing that inventory page returns
	@Test
	public void testInventoryPage() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/inventory").accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("inventoryArray"))
		.andExpect(MockMvcResultMatchers.view().name("inventoryView-kne-58"));
	}
	
	//Test For Successful Inventory Creation
	@Test
	public void testInventoryCreate() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/inventory/create")
				.param("itemType", "Keyboard")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("messages"))
		.andExpect(model().attribute("messages", "Created Item: Keyboard"));
	}
	
	//Testing that the hardwareAssign page returns
	@Test
	public void testInventoryAssignPage() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/inventory/assign").accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("ticketArray"))
		.andExpect(MockMvcResultMatchers.view().name("hardwareAssignPage-kne-58"));
	}
	
	//Testing for ticket to assign to inventory
	@Test
	public void testInventoryAssign() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/inventory/assign/assignTo/{ticketID}/{hardware}")
				.param("assignedTo", "1")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("inventory"))
		.andExpect(model().attribute("inventory", "upInventory"));
	}

}
