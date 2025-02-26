package org.ps.ecp.ecom.inventory.service;

import org.ps.ecp.ecom.inventory.entities.SkuEntity;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.ps.ecp.ecom.inventory.pojo.Sku;

import java.util.List;

public interface SkuCRUDService {

    ServiceResponse addNewSku(Sku sku);

    ServiceResponse addAll(List<Sku> sku);

    ServiceResponse fetchSkuDetails(String skuId);

    List<SkuEntity> getAllSkus();
}
