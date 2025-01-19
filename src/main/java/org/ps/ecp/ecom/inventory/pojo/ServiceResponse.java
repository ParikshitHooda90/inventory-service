package org.ps.ecp.ecom.inventory.pojo;

@lombok.Data
public class ServiceResponse {
    private int statusCode;
    private String status;
    private Object data;
    private String message;
}
