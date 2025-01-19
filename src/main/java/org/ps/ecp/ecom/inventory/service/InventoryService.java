package org.ps.ecp.ecom.inventory.service;


import org.ps.ecp.ecom.inventory.pojo.InventoryNode;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;

import java.util.List;

public interface InventoryService {

    ServiceResponse addNewEntryToInventory(InventoryNode inventoryNode);

    ServiceResponse addAllToInventory(List<InventoryNode> inventoryNode);

    ServiceResponse fetchInventoryItemsForSkus(List<String> skuIds);

    ServiceResponse getCompleteInventory();

    ServiceResponse incrementSkuQuanity(String skuId, Integer quantity );

    ServiceResponse reduceSkuQuanity(String skuId, Integer quantity );

}

