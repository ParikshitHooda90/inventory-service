package org.ps.ecp.ecom.inventory.service.impl;

import org.ps.ecp.ecom.inventory.entities.SkuEntity;
import org.ps.ecp.ecom.inventory.helper.CommonHelper;
import org.ps.ecp.ecom.inventory.helper.SequenceGeneratorHelper;
import org.ps.ecp.ecom.inventory.pojo.ResponseStatus;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.ps.ecp.ecom.inventory.pojo.Sku;
import org.ps.ecp.ecom.inventory.repository.SkuRepository;
import org.ps.ecp.ecom.inventory.service.SkuCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkuCRUDServiceImpl implements SkuCRUDService {

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SequenceGeneratorHelper sequenceGeneratorHelper;

    @Override
    public ServiceResponse addNewSku(Sku sku) {
        SkuEntity skuEntity = SkuEntity.builder()
                .skuId("SKU"+sequenceGeneratorHelper.generateSkuSequenceCounter())
                .name(sku.getName())
                .productId(sku.getProductId())
                .color(sku.getColor())
                .build();
        SkuEntity savedEntity = skuRepository.save(skuEntity);
        return CommonHelper.buildServiceResponse(savedEntity,200, ResponseStatus.Success,"successfully added sku");
    }

    @Override
    public ServiceResponse addAll(List<Sku> skus) {
        List<SkuEntity> skuEntities = skus.stream().map(sku -> SkuEntity.builder()
                .skuId("SKU"+sequenceGeneratorHelper.generateSkuSequenceCounter())
                .productId(sku.getProductId())
                .name(sku.getName())
                .color(sku.getColor())
                .build()
        ).toList();
        List<SkuEntity> saved = skuRepository.saveAll(skuEntities);
        return CommonHelper.buildServiceResponse(saved,200, ResponseStatus.Success,"successfully added sku");

    }

    @Override
    public ServiceResponse fetchSkuDetails(String skuId) {
        Optional<SkuEntity> skuEntityOptional = skuRepository.findById(skuId);
        return skuEntityOptional
                .map(skuEntity -> CommonHelper.buildServiceResponse(skuEntity, 200, ResponseStatus.Success, "successfully fetched sku quantity"))
                .orElseGet(() -> CommonHelper.buildServiceResponse(null, 200, ResponseStatus.Failure, "Unable to fetch sku by provided details"));
    }

    @Override
    public List<SkuEntity> getAllSkus() {
        List<SkuEntity> skuEntities = skuRepository.findAll();
        return skuEntities;
    }


}
