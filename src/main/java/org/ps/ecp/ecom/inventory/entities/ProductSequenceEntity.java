package org.ps.ecp.ecom.inventory.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ps.ecp.ecom.inventory.constants.InventoryConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = InventoryConstants.PRODUCT_SEQUENCE)
public class ProductSequenceEntity {
    @Id
    private String id;

    @Getter
    @Setter
    private long seq;
}
