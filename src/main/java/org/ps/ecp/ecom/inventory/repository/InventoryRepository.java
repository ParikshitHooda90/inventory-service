package org.ps.ecp.ecom.inventory.repository;

import org.ps.ecp.ecom.inventory.entities.InventoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryEntity,Long> {

    Optional<InventoryEntity> findBySkuId(String skuId);
    Optional<InventoryEntity> findByProductId(String productId);

    List<InventoryEntity> findAllBySkuId(List<String> skuIds);

    @Query(value = "{quantity:0}")
    List<InventoryEntity> fetchOutOfStockItems();

}
