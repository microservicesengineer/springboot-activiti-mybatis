package com.ibm.vms.controller;

import java.util.Map;

import javax.validation.Valid;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ibm.vms.service.ActivitiService;
import com.vms.controller.ProcessInstanceManagmentApi;
import com.vms.model.InlineResponse2002;
import com.vms.model.InlineResponse2003;
import com.vms.model.ProcessInstanceEntity;
import com.vms.model.StandardResponse;
import com.vms.model.StartProcessInstanceReqVO;

public class ProcessionInstanceController implements ProcessInstanceManagmentApi{

    @Autowired
    ActivitiService activitiService;
	
	@Override
	public ResponseEntity<StandardResponse> createProcessInstance(
			@Valid StartProcessInstanceReqVO startProcessInstanceReq) {
		// TODO Auto-generated method stub
        Map<String, Object> variables = startProcessInstanceReq.getVariables();//流程配置参数
        variables.put("applyUserId", startProcessInstanceReq.getApplyUserId());//流程发起人
        ProcessInstance processInstance = activitiService.startProcessInstance(startProcessInstanceReq.getInstanceKey(), startProcessInstanceReq.getBusinessKey(), variables);
		return ProcessInstanceManagmentApi.super.createProcessInstance(startProcessInstanceReq);
	}

	@Override
	public ResponseEntity<StandardResponse> deleteProcessInstanceByID(String id) {
		// TODO Auto-generated method stub
		activitiService.deleteProcessInstanceById(id);
		return ProcessInstanceManagmentApi.super.deleteProcessInstanceByID(id);
	}

	@Override
	public ResponseEntity<ProcessInstanceEntity> queryProcessInstanceByID(String id) {
		// TODO Auto-generated method stub
		activitiService.queryProcessInstanceById(id);
		return ProcessInstanceManagmentApi.super.queryProcessInstanceByID(id);
	}

	@Override
	public ResponseEntity<InlineResponse2002> queryProcessInstanceByKey(String key) {
		// TODO Auto-generated method stub
		activitiService.queryProcessInstanceByKey(key);
		return ProcessInstanceManagmentApi.super.queryProcessInstanceByKey(key);
	}

	@Override
	public ResponseEntity<InlineResponse2003> queryTaskHistoricalDataByID(Integer id) {
		// TODO Auto-generated method stub
		return ProcessInstanceManagmentApi.super.queryTaskHistoricalDataByID(id);
	}



}
