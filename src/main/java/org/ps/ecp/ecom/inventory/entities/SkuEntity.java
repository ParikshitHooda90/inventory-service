package org.ps.ecp.ecom.inventory.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "sku")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuEntity {

    @Id
    @Field("skuId")
    private String skuId;

    @Field("skuName")
    private String name;

    @Field("productId")
    private String productId;

    @Field("color")
    private String color;


}
