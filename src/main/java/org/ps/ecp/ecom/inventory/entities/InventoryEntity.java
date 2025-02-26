package org.ps.ecp.ecom.inventory.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "inventory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryEntity {

    @Id
    private Long id;

    @Field("productId")
    private String productId;

    @Field("skuId")
    private String skuId;

    @Field("quantity")
    private Integer quantity;

}
