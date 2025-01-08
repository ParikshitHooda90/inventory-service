package org.ps.ecp.ecom.service;

import org.ps.ecp.ecom.entities.SkuEntity;
import org.ps.ecp.ecom.pojo.ServiceResponse;
import org.ps.ecp.ecom.pojo.Sku;

import java.util.List;

public interface SkuCRUDService {

    ServiceResponse addNewSku(Sku sku);

    ServiceResponse addAll(List<Sku> sku);

    ServiceResponse fetchSkuDetails(String skuId);

    List<SkuEntity> getAllSkus();
}
