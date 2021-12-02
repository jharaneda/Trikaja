package com.csis3275.WhiteTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.csis3275.model.TicketModel_jar_86;

class TicketModelTest_jar_86 {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	TicketModel_jar_86 ticket = new TicketModel_jar_86();

	@Test
	void testStatus() {
		ticket.setStatus("Open");
		assertEquals("Open", ticket.getStatus());
	}
	
	@Test
	void testUserCreator() {
		ticket.setUserCreator("Kneale95");
		assertEquals("Kneale95", ticket.getUserCreator());
	}
	
	@Test
	void testPriority() {
		ticket.setPriority("Low");
		assertEquals("Low", ticket.getPriority());
	}
	
	@Test
	void testHardwareToBeChanged() {
		ticket.setHardwareToBeChanged("Headset");
		assertEquals("Headset", ticket.getHardwareToBeChanged());
	}

}
