package org.ps.ecp.ecom.inventory.helper;

import org.ps.ecp.ecom.inventory.constants.InventoryConstants;
import org.ps.ecp.ecom.inventory.entities.InventorySequenceEntity;
import org.ps.ecp.ecom.inventory.entities.ProductSequenceEntity;
import org.ps.ecp.ecom.inventory.entities.SkuSequenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorHelper {

    @Autowired
    private MongoOperations operations;

    public long generateProductsSequenceCounter() {
        // get the sequence number
        final Query q = new Query(Criteria.where("_id").is(InventoryConstants.PRODUCT_SEQUENCE));
        // increment the sequence number by 1
        // "sequence" should match the attribute value specified in DbSequence.java class.
        final Update u = new Update().inc("seq", 1);
        // modify in document
        final ProductSequenceEntity counter = operations.findAndModify(q, u,
                FindAndModifyOptions.options().returnNew(true).upsert(true), ProductSequenceEntity.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public long generateSkuSequenceCounter() {
        // get the sequence number
        final Query q = new Query(Criteria.where("_id").is(InventoryConstants.SKU_SEQUENCE));
        // increment the sequence number by 1
        // "sequence" should match the attribute value specified in DbSequence.java class.
        final Update u = new Update().inc("seq", 1);
        // modify in document
        final SkuSequenceEntity counter = operations.findAndModify(q, u,
                FindAndModifyOptions.options().returnNew(true).upsert(true), SkuSequenceEntity.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public long generateInventorySequenceCounter() {
        // get the sequence number
        final Query q = new Query(Criteria.where("_id").is(InventoryConstants.INVENTORY_SEQUENCE));
        // increment the sequence number by 1
        // "sequence" should match the attribute value specified in DbSequence.java class.
        final Update u = new Update().inc("seq", 1);
        // modify in document
        final InventorySequenceEntity counter = operations.findAndModify(q, u,
                FindAndModifyOptions.options().returnNew(true).upsert(true), InventorySequenceEntity.class);

        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
