package org.ps.ecp.ecom.helper;

import org.ps.ecp.ecom.pojo.EmptyResponse;
import org.ps.ecp.ecom.pojo.ResponseStatus;
import org.ps.ecp.ecom.pojo.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.random.RandomGenerator;

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
