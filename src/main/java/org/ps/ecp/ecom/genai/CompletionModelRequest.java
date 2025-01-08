package org.ps.ecp.ecom.genai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompletionModelRequest {
    private String model;
    private List<Message> messages;
}
