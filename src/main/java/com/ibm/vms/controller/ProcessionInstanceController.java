package com.ibm.vms.controller;

import javax.validation.Valid;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.vms.service.ProcessInstanceService;
import com.ibm.vms.util.HttpResponseBuilder;
import com.vms.controller.ProcessinstanceApi;
import com.vms.model.StandardResponse;
import com.vms.model.StartProcessInstanceReqVO;

@RestController
public class ProcessionInstanceController implements ProcessinstanceApi{

    @Autowired
    ProcessInstanceService mProcessInstanceService;

	@Override
	public ResponseEntity<StandardResponse> createProcessInstance(@Valid StartProcessInstanceReqVO body) {
		try {
			ProcessInstance instance = mProcessInstanceService.startProcessInstance(body.getInstanceKey(), body.getBusinessKey(), body.getVariables());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "create instance success", instance);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> deleteProcessInstanceByID(String id) {
		// TODO Auto-generated method stub
		return ProcessinstanceApi.super.deleteProcessInstanceByID(id);
	}

	@Override
	public ResponseEntity<StandardResponse> queryProcessInstanceByID(String id) {
		// TODO Auto-generated method stub
		return ProcessinstanceApi.super.queryProcessInstanceByID(id);
	}

	@Override
	public ResponseEntity<StandardResponse> queryProcessInstanceByKey(String key) {
		// TODO Auto-generated method stub
		return ProcessinstanceApi.super.queryProcessInstanceByKey(key);
	}

	@Override
	public ResponseEntity<StandardResponse> queryTaskHistoricalDataByID(Integer id) {
		// TODO Auto-generated method stub
		return ProcessinstanceApi.super.queryTaskHistoricalDataByID(id);
	}
	
	

}
