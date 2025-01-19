package org.ps.ecp.ecom.inventory.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ps.ecp.ecom.inventory.constants.InventoryConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = InventoryConstants.SKU_SEQUENCE)
@Builder
public class SkuSequenceEntity {

    @Id
    private String id;

    @Getter
    @Setter
    private long seq;
}
