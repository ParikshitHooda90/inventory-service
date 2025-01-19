package org.ps.ecp.ecom.inventory.genai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompletionModelRequest {
    private String model;
    private List<Message> messages;
}
