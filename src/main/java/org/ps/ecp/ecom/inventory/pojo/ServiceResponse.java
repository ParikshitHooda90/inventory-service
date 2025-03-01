package org.ps.ecp.ecom.inventory.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceResponse {
    private int statusCode;
    private String status;
    private Object data;
    private String message;
}
