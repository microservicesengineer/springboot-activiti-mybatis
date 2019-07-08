package com.ibm.vms.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vms.model.StandardResponse;

public class HttpResponseBuilder {
	
	public static ResponseEntity<StandardResponse> success(Integer code, String message, Object data){
		StandardResponse response  = new StandardResponse();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	public static ResponseEntity<StandardResponse> fail(Integer code, String message, Object data){
		StandardResponse response  = new StandardResponse();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}

}
