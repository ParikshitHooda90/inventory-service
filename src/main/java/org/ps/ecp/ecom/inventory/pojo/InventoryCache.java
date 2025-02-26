package org.ps.ecp.ecom.inventory.pojo;

import lombok.*;
import org.ps.ecp.ecom.inventory.entities.InventoryEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryCache {
    private List<InventoryEntity> inventory;
}
