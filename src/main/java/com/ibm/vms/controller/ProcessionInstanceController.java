package com.ibm.vms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.vms.api.ProcessinstanceApi;
import com.ibm.vms.models.ChangeInstanceStatusReqVO;
import com.ibm.vms.models.Formmodel;
import com.ibm.vms.models.Instancemodel;
import com.ibm.vms.models.StandardResponse;
import com.ibm.vms.models.StartProcessInstanceReqVO;
import com.ibm.vms.service.ProcessInstanceService;
import com.ibm.vms.util.HttpResponseBuilder;

@RestController
public class ProcessionInstanceController implements ProcessinstanceApi{

    @Autowired
    ProcessInstanceService mProcessInstanceService;
    
 

	@Override
	public ResponseEntity<StandardResponse> createProcessInstance(@Valid StartProcessInstanceReqVO body) {
		
		try {
			Instancemodel instance = mProcessInstanceService.startProcessInstance(body.getInstanceKey(), body.getBusinessKey(), body.getVariables());
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "create instance success", instance);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> deleteProcessInstanceByID(String id) {
		// TODO Auto-generated method stub
		try {
			mProcessInstanceService.deleteProcessInstanceById(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "delete instance success", null);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> queryProcessInstanceByID(String id) {
		// TODO Auto-generated method stub		
		try {
			List<Formmodel> data = mProcessInstanceService.queryProcessInstanceById(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "query instance success", data);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> changeInstanctStatusById(String id, ChangeInstanceStatusReqVO body) {
		// TODO Auto-generated method stub
		try {
			String data = mProcessInstanceService.changeInstanctStatusById(id,body.getStatus());
			if(body.getStatus().equals(1)) {
				return HttpResponseBuilder.success(HttpStatus.OK.value(), "suspend instance success", data);
			}else if (body.getStatus().equals(0)) {
				return HttpResponseBuilder.success(HttpStatus.OK.value(), "activate instance success", data);
			}else {
				return HttpResponseBuilder.success(HttpStatus.OK.value(), "suspend/activate instance Error", data);
			}
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}	}



	@Override
	public ResponseEntity<StandardResponse> queryProcessInstanceByKey(String key) {
		// TODO Auto-generated method stub
		try {
			List data = mProcessInstanceService.queryProcessInstanceByKey(key);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "query instance success", data);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> queryTaskHistoricalDataByID(String id) {
		try {
			List data = mProcessInstanceService.queryTaskHistory(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "query history success", data);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}
}