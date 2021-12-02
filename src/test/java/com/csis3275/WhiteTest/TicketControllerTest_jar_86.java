package com.csis3275.WhiteTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.csis3275.controller.TicketController_jar_86;

//@ComponentScan({"com.csis3275", "com.csis3275.controller", "com.csis3275.dao"})
@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController_jar_86.class)
class TicketControllerTest_jar_86 {
	
	@Autowired
	private MockMvc mvc;
	

	@Test
	void testHomePage() throws Exception{
		mvc.perform(MockMvcRequestBuilders
				.get("/")
				.accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("login-jar-86"));
	}

}
