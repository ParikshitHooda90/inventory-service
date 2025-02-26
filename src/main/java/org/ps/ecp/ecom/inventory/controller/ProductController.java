package org.ps.ecp.ecom.inventory.controller;

import org.ps.ecp.ecom.inventory.pojo.Product;
import org.ps.ecp.ecom.inventory.service.ProductsCRUDService;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory/product")
public class ProductController {

    @Autowired
    private ProductsCRUDService productsCRUDService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        ServiceResponse response = productsCRUDService.addProduct(product);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/fetchDetails")
    public ResponseEntity<?> fetchProductDetails(@RequestBody Product product){
        ServiceResponse response = productsCRUDService.fetchProductDetails(product.getProductId());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product){
        ServiceResponse response = productsCRUDService.deleteProduct(product);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody Product product){
        ServiceResponse response = productsCRUDService.editProduct(product);
        return ResponseEntity.ok().body(response);
    }
}
