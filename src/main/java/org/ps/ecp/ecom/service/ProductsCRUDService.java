package org.ps.ecp.ecom.service;

import org.ps.ecp.ecom.pojo.Product;
import org.ps.ecp.ecom.pojo.ServiceResponse;

public interface ProductsCRUDService {

    public ServiceResponse addProduct(Product productInfo);

    public ServiceResponse deleteProduct(Product product);

    public ServiceResponse editProduct(Product product);

    public ServiceResponse fetchProductDetails(String  productId);
}
