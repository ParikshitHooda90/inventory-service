package org.ps.ecp.ecom.inventory.service.impl;

import org.ps.ecp.ecom.inventory.entities.InventoryEntity;
import org.ps.ecp.ecom.inventory.helper.CommonHelper;
import org.ps.ecp.ecom.inventory.helper.SequenceGeneratorHelper;
import org.ps.ecp.ecom.inventory.pojo.InventoryNode;
import org.ps.ecp.ecom.inventory.pojo.ResponseStatus;
import org.ps.ecp.ecom.inventory.repository.InventoryRepository;
import org.ps.ecp.ecom.inventory.service.InventoryService;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private SequenceGeneratorHelper sequenceGeneratorHelper;

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
        List<InventoryEntity> inventoryItems = inventoryRepository.findAll();
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
