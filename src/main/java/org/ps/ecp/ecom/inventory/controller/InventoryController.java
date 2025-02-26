package org.ps.ecp.ecom.inventory.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ps.ecp.ecom.inventory.entities.InventoryEntity;
import org.ps.ecp.ecom.inventory.entities.SkuEntity;
import org.ps.ecp.ecom.inventory.genai.SandBoxGptUtils;
import org.ps.ecp.ecom.inventory.service.InventoryService;
import org.ps.ecp.ecom.inventory.service.SkuCRUDService;
import org.ps.ecp.ecom.inventory.pojo.InventoryNode;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SkuCRUDService skuCRUDService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    SandBoxGptUtils sandBoxGptUtils;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("hello from inventory-service");
    }


    @GetMapping("/all")
    public ResponseEntity<?> getCompleteInventory(){
        return  ResponseEntity.ok().body(inventoryService.getCompleteInventory());
    }

    @GetMapping("/fetchBySku")
    public ResponseEntity<?> fetchBySkus(@RequestParam("sku") List<String> skus){
        return  ResponseEntity.ok().body(inventoryService.fetchInventoryItemsForSkus(skus));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody InventoryNode inventoryNode){
        return  ResponseEntity.ok().body(inventoryService.addNewEntryToInventory(inventoryNode));
    }

    @PostMapping("/bulk/add")
    public ResponseEntity<?> addAll(@RequestBody List<InventoryNode> inventoryNodes){
        return  ResponseEntity.ok().body(inventoryService.addAllToInventory(inventoryNodes));
    }

    @PostMapping("/increment/sku")
    public ResponseEntity<?> increment(@RequestBody InventoryNode node){
        return  ResponseEntity.ok().body(inventoryService.incrementSkuQuanity(node.getSkuId(), node.getQuantity()));
    }
    @PostMapping("/reduce/sku")
    public ResponseEntity<?> reduce(@RequestBody InventoryNode node){
        return  ResponseEntity.ok().body(inventoryService.reduceSkuQuanity(node.getSkuId(), node.getQuantity()));
    }

    @GetMapping("/genAI/summary")
    public ResponseEntity<?> genAiSummary(){
        List<SkuEntity> skuEntities = skuCRUDService.getAllSkus();
        ServiceResponse inventoryEntitiesResp = inventoryService.getCompleteInventory();
        List<InventoryEntity> inventories = (List<InventoryEntity>) inventoryEntitiesResp.getData();

        try {
            JsonNode skuJson = objectMapper.readTree(objectMapper.writeValueAsBytes(skuEntities));
            JsonNode inventoryJson = objectMapper.readTree(objectMapper.writeValueAsBytes(inventories));
            String prompt = "As a content writer. Write a summary for the json provided below having all possible metrics like count, name, color etc. Inventory data contains available count for sku for every skuId and Sku data have the information for every skuid in inventory data. "
                    +"Inventory data:"+inventoryJson+ " and sku data is: "+ skuJson;
            //TODO: Get data from genAI. model = gpt-4o, api = openai.ai/chat/completions
            String promptResponse = sandBoxGptUtils.getChatCompletionMessage(prompt);
            return ResponseEntity.ok().body(promptResponse);
        } catch (IOException e) {
            return ResponseEntity.ok().body("Unable to generate inventory summary");
        }


    }

}
