package org.ps.ecp.ecom.inventory.repository;

import org.ps.ecp.ecom.inventory.entities.SkuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuRepository extends MongoRepository<SkuEntity,String> {

    List<SkuEntity> findAllByProductId(String productId);

}
