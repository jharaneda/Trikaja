package com.csis3275.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.csis3275.model.InventoryModel_kne_58;

class InventoryModelTest {
	
	InventoryModel_kne_58 inventoryTest = new InventoryModel_kne_58();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	//Testing the item ID setter/getter
	@Test
	void testItemID() {
		inventoryTest.setItemID(9);
		assertEquals(9, inventoryTest.getItemID());
	}
	
	//Testing the Item Location setter/getter
	@Test
	void testItemLocation() {
		inventoryTest.setItemLocation("Warehouse");;
		assertEquals("Warehouse", inventoryTest.getItemLocation());
	}
	
	//Testing the Item Type setter/getter
	@Test
	void testItemType() {
		inventoryTest.setItemType("HeadSet");;
		assertEquals("HeadSet", inventoryTest.getItemType());
	}

	//Testing the Item Assignment setter/getter
	@Test
	void testAssignedTo() {
		inventoryTest.setAssignedTo(22);;
		assertEquals(22, inventoryTest.getAssignedTo());
	}

	//Testing the Item Status setter/getter
	@Test
	void testStatus() {
		inventoryTest.setStatus("Broken");;
		assertEquals("Broken", inventoryTest.getStatus());
	}

}
