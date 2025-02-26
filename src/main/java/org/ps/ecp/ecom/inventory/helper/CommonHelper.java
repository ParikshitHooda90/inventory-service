package org.ps.ecp.ecom.inventory.helper;

import org.ps.ecp.ecom.inventory.pojo.ResponseStatus;
import org.ps.ecp.ecom.inventory.pojo.ServiceResponse;
import org.springframework.stereotype.Service;

@Service
public class CommonHelper {

    public static ServiceResponse buildServiceResponse(Object data, int statusCode, ResponseStatus responseStatus, String message){
        ServiceResponse serviceResponse= new ServiceResponse();
        serviceResponse.setData((data == null) ? "" : data);
        serviceResponse.setStatus(responseStatus.name());
        serviceResponse.setMessage(message);
        serviceResponse.setStatusCode(statusCode);
        return serviceResponse;
    }

}
