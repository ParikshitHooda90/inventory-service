package org.ps.ecp.ecom.service;


import org.ps.ecp.ecom.pojo.InventoryNode;
import org.ps.ecp.ecom.pojo.ServiceResponse;

import java.util.List;

public interface InventoryService {

    ServiceResponse addNewEntryToInventory(InventoryNode inventoryNode);

    ServiceResponse addAllToInventory(List<InventoryNode> inventoryNode);

    ServiceResponse fetchInventoryItemsForSkus(List<String> skuIds);

    ServiceResponse getCompleteInventory();

    ServiceResponse incrementSkuQuanity(String skuId, Integer quantity );

    ServiceResponse reduceSkuQuanity(String skuId, Integer quantity );

}

