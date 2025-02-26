package org.ps.ecp.ecom.inventory.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryNode {
    private String productId;
    private String skuId;
    private Integer quantity;
}
