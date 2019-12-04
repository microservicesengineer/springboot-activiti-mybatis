package com.ibm.vms.controller;

import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.vms.api.ProcessdefinitionApi;
import com.ibm.vms.models.ProcessDefinitionmodel;
import com.ibm.vms.models.StandardResponse;
import com.ibm.vms.service.ProcessDefinitionService;
import com.ibm.vms.util.HttpResponseBuilder;

@RestController
public class ProcessDefinitationController implements ProcessdefinitionApi {
	@Autowired
	ProcessDefinitionService mProcessDefinitionService;

	@Autowired
	HistoryService historyService;

	@Autowired
	RepositoryService repositoryService;

	@Override
	public ResponseEntity<StandardResponse> deleteProcessDefinitionById(String id) {
		// TODO Auto-generated method stub
		mProcessDefinitionService.deleteDeploymentbyid(id);
		try {
			mProcessDefinitionService.deleteDeploymentbyid(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "delete deploy success", null);
		} catch(Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> deployPreDefinedProcessDefinition(@NotNull @Valid String bpmnName,
			@Valid String category) {
		try {
			if (StringUtils.isBlank(bpmnName))
				return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), "bpmn file name could be empty", null);
			mProcessDefinitionService.deploy(bpmnName, category);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "deploy successfully", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> deployProcessDefinitionByUploadZip(@Valid MultipartFile file,
			@Valid String bpmnName, @Valid String category) {
		try {
			if (file == null && StringUtils.isBlank(bpmnName))
				return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(),
						"Please select a file to upload or input BPMN file name.", null);

			if (file != null && !file.isEmpty()) { // publish the zip file
				String fileName = file.getOriginalFilename();
				if (StringUtils.isBlank(fileName))
					return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), "bpmn file name could be empty",
							null);
				InputStream fileInputStream = file.getInputStream();
				mProcessDefinitionService.deploy(fileInputStream, fileName, category);
			} else {
				mProcessDefinitionService.deploy(bpmnName, category);
			}
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "deploy successfully", null);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}

	@Override
	public ResponseEntity<StandardResponse> getProcessDefinitionById(String id) {
		// TODO Auto-generated method stub
     try {
    	 ProcessDefinitionmodel ProcessDefinitionmodel = mProcessDefinitionService.deploymentQuerybyid(id);
		return HttpResponseBuilder.success(HttpStatus.OK.value(), "fetch task list success", ProcessDefinitionmodel);
	} catch (Exception e) {
		return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
	}
	}

	@Override
	public ResponseEntity<StandardResponse> getProcessDefinitionDeploymentList() {
	     try {
	    	 List<ProcessDefinitionmodel> list = mProcessDefinitionService.deploymentQuery();
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "fetch task list success", list);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}
	

	@Override
	public ResponseEntity<StandardResponse> queryrecall(String id) {
		// TODO Auto-generated method stub
	     try {
				 List data = mProcessDefinitionService.queryrecalltask(id);
			return HttpResponseBuilder.success(HttpStatus.OK.value(), "fetch task list success", data);
		} catch (Exception e) {
			return HttpResponseBuilder.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
		}
	}



}
