package com.csis3275.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csis3275.model.InventoryModel_kne_58;
import com.csis3275.model.InventoryRowMapper_kne_58;

@Service
public class InventoryDAOImp_kne_58 {

	JdbcTemplate jdbcTemplate;

	private final String SQL_GET_ALL_INVENTORY = "SELECT * FROM inventory";
	private final String SQL_CREATE_INVENTORY = "INSERT INTO inventory (itemLocation, itemType, assignedTo, status) VALUES (?,?,?,?)";
	private final String SQL_DELETE_INVENTORY = "DELETE FROM inventory WHERE itemID = ?";
	private final String SQL_UPDATE_INVENTORY = "UPDATE inventory set itemLocation = ?, itemType = ?, assignedTo = ?, status = ? WHERE itemID = ?";
	private final String SQL_FIND_INVENTORY = "SELECT * FROM inventory WHERE itemID = ?";

	@Autowired
	public InventoryDAOImp_kne_58(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<InventoryModel_kne_58> getInventory_kne_58() {
		ArrayList<InventoryModel_kne_58> inventory = new ArrayList<InventoryModel_kne_58>();

		inventory = (ArrayList<InventoryModel_kne_58>) jdbcTemplate.query(SQL_GET_ALL_INVENTORY,
				new InventoryRowMapper_kne_58());

		return inventory;
	}

	public boolean createInventory_kne_58(InventoryModel_kne_58 newInventory) {
		return jdbcTemplate.update(SQL_CREATE_INVENTORY, newInventory.getItemLocation(), newInventory.getItemType(),
				newInventory.getAssignedTo(), newInventory.getStatus()) > 0;
	}

	public boolean deleteInventory(int itemID) {
		return jdbcTemplate.update(SQL_DELETE_INVENTORY, itemID) > 0;
	}

	public boolean updateInventory(InventoryModel_kne_58 inventory) {
		return jdbcTemplate.update(SQL_UPDATE_INVENTORY, inventory.getItemLocation(), inventory.getItemType(),
				inventory.getAssignedTo(), inventory.getStatus(), inventory.getItemID()) > 0;
	}
	
	@SuppressWarnings("deprecation")
	public InventoryModel_kne_58 findInventoryByID(int itemID) {
		return jdbcTemplate.queryForObject(SQL_FIND_INVENTORY, new Object[] {itemID}, new InventoryRowMapper_kne_58());
	}
}
