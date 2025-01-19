package org.ps.ecp.ecom.inventory.service.impl;

import org.ps.ecp.ecom.inventory.entities.ProductsEntity;
import org.ps.ecp.ecom.inventory.helper.CommonHelper;
import org.ps.ecp.ecom.inventory.helper.SequenceGeneratorHelper;
import org.ps.ecp.ecom.inventory.pojo.Product;
import org.ps.ecp.ecom.inventory.pojo.ResponseStatus;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.ps.ecp.ecom.inventory.repository.ProductRepository;
import org.ps.ecp.ecom.inventory.service.ProductsCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsCRUDServiceImpl implements ProductsCRUDService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    SequenceGeneratorHelper sequenceGeneratorHelper;

    @Override
    public ServiceResponse addProduct(Product productInfo) {
        ProductsEntity productsEntity = ProductsEntity.builder()
                .productId("PR"+ sequenceGeneratorHelper.generateProductsSequenceCounter())
                .name(productInfo.getName())
                .brand(productInfo.getBrand())
                .category(productInfo.getCategory())
                .build();
        ProductsEntity savedEntity = productRepository.save(productsEntity);
        return CommonHelper.buildServiceResponse(savedEntity,200, ResponseStatus.Success,"successfully added product");
    }

    @Override
    public ServiceResponse deleteProduct(Product product) {
         productRepository.deleteById(product.getProductId());
        return CommonHelper.buildServiceResponse(null,200, ResponseStatus.Success,"successfully added product");

    }

    @Override
    public ServiceResponse editProduct(Product product) {
        Optional<ProductsEntity> productsEntityOpt = productRepository.findById(product.getProductId());
        if(productsEntityOpt.isPresent()){
            ProductsEntity entity =  productsEntityOpt.get();
            if(product.getBrand()!=null){
                entity.setBrand(product.getBrand());
            }

            if(product.getCategory()!=null){
                entity.setCategory(product.getCategory());
            }
            if(product.getName()!=null){
                entity.setName(product.getName());
            }
            ProductsEntity savedEntity = productRepository.save(entity);
            return CommonHelper.buildServiceResponse(savedEntity,200, ResponseStatus.Success,"successfully added product");

        }
        return CommonHelper.buildServiceResponse(null,200, ResponseStatus.Failure,"Failed to update product");
    }

    @Override
    public ServiceResponse fetchProductDetails(String productId) {
        Optional<ProductsEntity> productsEntityOpt = productRepository.findByProductId(productId);
        return productsEntityOpt
                .map(productsEntity -> CommonHelper.buildServiceResponse(productsEntity, 200, ResponseStatus.Success, "successfully fetched sku quantity"))
                .orElseGet(() -> CommonHelper.buildServiceResponse(null, 200, ResponseStatus.Failure, "Unable to fetch sku by provided details"));

    }
}
