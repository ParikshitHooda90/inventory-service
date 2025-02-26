package org.ps.ecp.ecom.inventory.repository;

import org.ps.ecp.ecom.inventory.entities.ProductsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<ProductsEntity,String> {

    List<ProductsEntity> findAllByCategory(String category);

    Optional<ProductsEntity> findByProductId(String skuId);

    Optional<ProductsEntity> findByName(String name);

    @Query(value = "{name: { $in: ?0 }}", fields = "{ 'productId' : 1, 'name' : 1}")
    List<ProductsEntity> findAllContainsProductName(String productName);



}
