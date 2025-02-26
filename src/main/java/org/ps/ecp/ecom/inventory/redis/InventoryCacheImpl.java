package org.ps.ecp.ecom.inventory.redis;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.ps.ecp.ecom.inventory.pojo.InventoryCache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;


@Repository
public class InventoryCacheImpl implements InventoryCacheDao{

    private final String hashReference= "Inventory";
    private final static String INVENTORY_CACHE = "inventory-cache";

    @Resource(name="redisTemplate")          // 'redisTemplate' is defined as a Bean in AppConfig.java
    private HashOperations<String, String, String> hashOperations;

    @Override
    public void saveInventory(String inventoryCache) {
        hashOperations.put(hashReference, INVENTORY_CACHE, inventoryCache);
    }

    @Override
    public String fetchInventory() {
         return hashOperations.get(hashReference, INVENTORY_CACHE);
    }
}
