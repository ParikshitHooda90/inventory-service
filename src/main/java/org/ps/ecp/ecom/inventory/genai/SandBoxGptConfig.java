package org.ps.ecp.ecom.inventory.genai;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "sandboxgpt")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SandBoxGptConfig {
    private boolean enable;
    private String baseurl;
    private String authorization;
    private String solution;
    private String instructions;
    private String assistant;
    private String role;
}
