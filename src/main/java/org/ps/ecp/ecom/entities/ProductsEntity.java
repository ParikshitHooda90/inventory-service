package org.ps.ecp.ecom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsEntity {

    @Id
    @Field("productId")
    private String productId;

    @Field("name")
    private String name;

    @Field("category")
    private String category;

    @Field("brand")
    private String brand;



}
