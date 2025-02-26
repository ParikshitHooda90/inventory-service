package org.ps.ecp.ecom.inventory.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ps.ecp.ecom.inventory.entities.InventoryEntity;
import org.ps.ecp.ecom.inventory.helper.CommonHelper;
import org.ps.ecp.ecom.inventory.helper.SequenceGeneratorHelper;
import org.ps.ecp.ecom.inventory.pojo.InventoryCache;
import org.ps.ecp.ecom.inventory.pojo.InventoryNode;
import org.ps.ecp.ecom.inventory.pojo.ResponseStatus;
import org.ps.ecp.ecom.inventory.redis.InventoryCacheDao;
import org.ps.ecp.ecom.inventory.repository.InventoryRepository;
import org.ps.ecp.ecom.inventory.service.InventoryService;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SequenceGeneratorHelper sequenceGeneratorHelper;

    @Autowired
    private InventoryCacheDao inventoryCacheDao;

    @Autowired private ObjectMapper objectMapper;

    @Override
    public ServiceResponse addNewEntryToInventory(InventoryNode inventoryNode) {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .id(sequenceGeneratorHelper.generateInventorySequenceCounter())
                .productId(inventoryNode.getProductId())
                .skuId(inventoryNode.getSkuId())
                .quantity(inventoryNode.getQuantity())
                .build();
        InventoryEntity savedEntity = inventoryRepository.save(inventoryEntity);
        return CommonHelper.buildServiceResponse(savedEntity,200, ResponseStatus.Success,"Added to inventory");
    }

    @Override
    public ServiceResponse addAllToInventory(List<InventoryNode> inventoryNodes) {
        List<InventoryEntity> inventoryEntities = inventoryNodes.stream().map(node ->
                InventoryEntity.builder()
                        .id(sequenceGeneratorHelper.generateInventorySequenceCounter())
                        .productId(node.getProductId())
                        .skuId(node.getSkuId())
                        .quantity(node.getQuantity())
                        .build()
        ).toList();
        List<InventoryEntity> savedEntities = inventoryRepository.saveAll(inventoryEntities);
        return CommonHelper.buildServiceResponse(savedEntities, 200, ResponseStatus.Success, "Added all to inventory");
    }

    @Override
    public ServiceResponse fetchInventoryItemsForSkus(List<String> skuIds) {
        List<InventoryEntity> inventoryItems = inventoryRepository.findAllBySkuId(skuIds);
        return CommonHelper.buildServiceResponse(inventoryItems,200,ResponseStatus.Success,"Successful");
    }

    @Override
    public ServiceResponse getCompleteInventory() {
        String inventoryCache = inventoryCacheDao.fetchInventory();

        Optional<String> inventoryItemsOpt =  Optional.ofNullable(inventoryCache);
        List<InventoryEntity> inventoryItems = null;
        try {
        if (inventoryItemsOpt.isEmpty()){
            inventoryItems = inventoryRepository.findAll();
            InventoryCache inventoryCache1 = new InventoryCache(inventoryItems);

            inventoryCacheDao.saveInventory(objectMapper.writeValueAsString(inventoryCache1));

        }else {
            InventoryCache inventoryCache1 = objectMapper.readValue(inventoryItemsOpt.get(), InventoryCache.class);
            inventoryItems = inventoryCache1.getInventory();
        }
        } catch (JsonProcessingException e) {
        logger.info("JsonProcessingException");
      }


        return CommonHelper.buildServiceResponse(inventoryItems,200,ResponseStatus.Success,"Successful");
    }

    @Override
    public ServiceResponse incrementSkuQuanity(String skuId, Integer quantity) {
        Optional<InventoryEntity> entityOpt = inventoryRepository.findBySkuId(skuId);
        if(entityOpt.isPresent()){
            InventoryEntity existingEntity = entityOpt.get();
            Integer existingQty = existingEntity.getQuantity();
            existingEntity.setQuantity(existingQty + quantity);
            InventoryEntity savedEntity = inventoryRepository.save(existingEntity);
            return CommonHelper.buildServiceResponse(savedEntity, 200, ResponseStatus.Success, "Sku updated");
        }
        return CommonHelper.buildServiceResponse(null, 200, ResponseStatus.Failure, "Unable to fetch sku by provided details");
    }

    @Override
    public ServiceResponse reduceSkuQuanity(String skuId, Integer quantity) {
        Optional<InventoryEntity> entityOpt = inventoryRepository.findBySkuId(skuId);
        if(entityOpt.isPresent()){
            InventoryEntity existingEntity = entityOpt.get();
            Integer existingQty = existingEntity.getQuantity();
            existingEntity.setQuantity(existingQty + quantity);
            InventoryEntity savedEntity = inventoryRepository.save(existingEntity);
            return CommonHelper.buildServiceResponse(savedEntity, 200, ResponseStatus.Success, "Sku updated");
        }
        return CommonHelper.buildServiceResponse(null, 200, ResponseStatus.Failure, "Unable to fetch sku by provided details");

    }
}
