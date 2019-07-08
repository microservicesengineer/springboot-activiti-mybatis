package com.ibm.vms.service.impl;

import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.vms.service.ProcessInstanceService;

@Service
public class ProcessInstanceServiceImpl implements ProcessInstanceService {
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

	/***
	 * 开始流程
	 * 
	 * @param instanceKey 流程实例key
	 * @param variables  参数
	 */
	public ProcessInstance startProcessInstance(String instanceKey, String businessKey, Map<String, Object> variables) {
		ProcessInstance processInstance = null;
		if (businessKey != null) {
			processInstance = runtimeService.startProcessInstanceByKey(instanceKey, businessKey, variables);
		} else {
			processInstance = runtimeService.startProcessInstanceByKey(instanceKey, variables);
		}
		return processInstance;
	}

	/***
	 * 删除流程实例
	 * 
	 * @param instanceKey
	 *            流程实例key
	 * @param variables
	 *            参数
	 */
	public void deleteProcessInstanceById(String processInstanceId) {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		if (pi == null) {
			// 该流程实例已经完成了
			historyService.deleteHistoricProcessInstance(processInstanceId);
		} else {
			// 该流程实例未结束的
			runtimeService.deleteProcessInstance(processInstanceId, "");
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
	}

	/***
	 * 查询流程实例
	 * 
	 * @param instanceKey
	 *            流程实例key
	 * @param variables
	 *            参数
	 */
	public void queryProcessInstanceById(String processInstanceId) {
		ProcessInstance pi = processEngine.getRuntimeService()// 表示正在执行的流程实例和执行对象
				.createProcessInstanceQuery()// 创建流程实例查询
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.singleResult();
		if (pi == null) {
			System.out.println("该流程实例走完");
		} else {
			System.out.println("该流程实例还没走完");
		}
	}

	/***
	 * 查询流程实例
	 * 
	 * @param instanceKey
	 *            流程实例key
	 */
	public void queryProcessInstanceByKey(String businessKey) {
		ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey).singleResult();
		if (pi == null) {
			System.out.println("该流程实例走完");
		} else {
			System.out.println("该流程实例还没走完");
		}
	}
}