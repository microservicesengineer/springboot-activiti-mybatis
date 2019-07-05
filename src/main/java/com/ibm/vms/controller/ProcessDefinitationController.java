package com.ibm.vms.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.vms.service.ActivitiService;
import com.vms.controller.BpmnProcessDefinitionManagmentApi;
import com.vms.model.InlineResponse200;
import com.vms.model.InlineResponse2001;
import com.vms.model.StandardResponse;

public class ProcessDefinitationController implements BpmnProcessDefinitionManagmentApi {
    @Autowired
    ActivitiService activitiService;

    @Autowired
    HistoryService historyService;
    
	@Autowired
    RepositoryService repositoryService;

    @Override
	public ResponseEntity<StandardResponse> deleteProcessDefinitionById(String id) {
		// TODO Auto-generated method stub
    	activitiService.deleteDeploymentbyid(id);
		return BpmnProcessDefinitionManagmentApi.super.deleteProcessDefinitionById(id);
	}

	@Override
	public ResponseEntity<StandardResponse> deployPreDefinedProcessDefinition(@NotNull @Valid String bpmnName,
			@Valid String category) {
		activitiService.deploy(bpmnName,category);
		// TODO Auto-generated method stub
		return BpmnProcessDefinitionManagmentApi.super.deployPreDefinedProcessDefinition(bpmnName, category);
	}

	@Override
	public ResponseEntity<InlineResponse2001> getProcessDefinitionById(String id) {
		// TODO Auto-generated method stub
		activitiService.deploymentQuerybyid(id);
		return BpmnProcessDefinitionManagmentApi.super.getProcessDefinitionById(id);
	}

	@Override
	public ResponseEntity<InlineResponse200> getProcessDefinitionDeploymentList() {
		// TODO Auto-generated method stub
		activitiService.deploymentQuery();
		return BpmnProcessDefinitionManagmentApi.super.getProcessDefinitionDeploymentList();
	}

}
