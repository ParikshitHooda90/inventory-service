package org.ps.ecp.ecom.inventory.controller;

import org.ps.ecp.ecom.inventory.pojo.Sku;
import org.ps.ecp.ecom.inventory.service.SkuCRUDService;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/sku")
public class SkuController {

    @Autowired
    private SkuCRUDService skuCRUDService;

    @PostMapping("/add")
    public ResponseEntity<?> addSku(@RequestBody Sku sku){
        ServiceResponse response = skuCRUDService.addNewSku(sku);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> addSku(@RequestBody List<Sku> sku){
        ServiceResponse response = skuCRUDService.addAll(sku);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/get")
    public ResponseEntity<?> get(@RequestBody Sku sku){
        ServiceResponse response = skuCRUDService.fetchSkuDetails(sku.getSkuId());
        return ResponseEntity.ok().body(response);
    }


}
