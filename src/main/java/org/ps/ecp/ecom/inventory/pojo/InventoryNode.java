package org.ps.ecp.ecom.inventory.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class InventoryNode {
    private String productId;
    private String skuId;
    private Integer quantity;
}
