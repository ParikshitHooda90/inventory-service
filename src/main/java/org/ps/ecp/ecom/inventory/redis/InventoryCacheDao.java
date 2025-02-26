package org.ps.ecp.ecom.inventory.redis;

import com.fasterxml.jackson.databind.JsonNode;
import org.ps.ecp.ecom.inventory.pojo.InventoryCache;

import java.util.List;

public interface InventoryCacheDao {

    void saveInventory(String inventoryEntities);

    String fetchInventory();
}
