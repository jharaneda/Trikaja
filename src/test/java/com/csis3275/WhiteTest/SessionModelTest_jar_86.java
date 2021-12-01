package com.csis3275.WhiteTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.csis3275.model.SessionModel_jar_86;

class SessionModelTest_jar_86 {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	SessionModel_jar_86 session = new SessionModel_jar_86();
	
	@Test
	void testEmail() {
		session.setEmail("Kneale95@hotmail.ca");
		assertEquals("Kneale95@hotmail.ca", session.getEmail());
	}
	
	@Test
	void testPosition() {
		session.setPosition("1");
		assertEquals("1", session.getPosition());
	}
}
