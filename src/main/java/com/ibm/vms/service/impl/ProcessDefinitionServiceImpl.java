package com.ibm.vms.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cmd.DeleteTaskCmd;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.vms.service.ProcessDefinitionService;

@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
	@Autowired
	ProcessEngine processEngine;

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	HistoryService historyService;

	@Autowired
	IdentityService identityService;

	@Autowired
	ManagementService managementService;

	/**
	 * deploy with category
	 *
	 * @param bpmnName
	 */

	public void deploy(String bpmnName, String category) {

		String bpmn = "processes/" + bpmnName + ".bpmn";

		repositoryService.createDeployment().name(bpmnName)// deploy name
				.addInputStream(bpmn, this.getClass().getClassLoader().getResourceAsStream(bpmn)).category(category) // add
																														// business
																														// category
				.deploy();// deploy
	}

	@Override
	public void deploy(InputStream fileInputStream, String fileName, String category) {
		ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
		repositoryService.createDeployment().addZipInputStream(zipInputStream).name(fileName).category(category)
				.deploy();

	}

	public List<Deployment> deploymentQuery() {
		List<Deployment> list = repositoryService.createDeploymentQuery().list();
		return list;
	}

	/***
	 * 根据部署ID查询部署
	 */
	public Deployment deploymentQuerybyid(String deploymentId) {
		Deployment Deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
		return Deployment;
	}

	/***
	 * 根据部署ID删除部署
	 */
	public void deleteDeploymentbyid(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}
}