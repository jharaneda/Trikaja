package com.csis3275.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InventoryRowMapper_kne_58  implements RowMapper<InventoryModel_kne_58>{

	@Override
	public InventoryModel_kne_58 mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InventoryModel_kne_58 inventory = new InventoryModel_kne_58();
		
		inventory.setItemID(rs.getInt("inventoryID"));
		inventory.setItemLocation(rs.getString("itemLocation"));
		inventory.setAssignedTo(rs.getInt("assignedTo"));
		inventory.setItemType(rs.getString("itemType"));
		inventory.setStatus(rs.getString("status"));
		
		return inventory;
	}

}
