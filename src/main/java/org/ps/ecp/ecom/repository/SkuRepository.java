package org.ps.ecp.ecom.repository;

import org.ps.ecp.ecom.entities.SkuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkuRepository extends MongoRepository<SkuEntity,String> {

    List<SkuEntity> findAllByProductId(String productId);

}
