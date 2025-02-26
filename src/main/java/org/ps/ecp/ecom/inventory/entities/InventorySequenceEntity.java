package org.ps.ecp.ecom.inventory.entities;

import lombok.*;
import org.ps.ecp.ecom.inventory.constants.InventoryConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = InventoryConstants.INVENTORY_SEQUENCE)
@Builder
public class InventorySequenceEntity {

    @Id
    private String id;

    @Getter
    @Setter
    private long seq;

}
