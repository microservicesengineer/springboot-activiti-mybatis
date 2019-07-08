package com.ibm.vms.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.vms.service.TaskFlowService;

@Service
public class TaskFlowServiceImpl implements TaskFlowService {

	@Autowired
	ProcessEngine mProcessEngine;

	@Autowired
	RepositoryService mRepositoryService;

	@Autowired
	RuntimeService mRuntimeService;

	@Autowired
	TaskService mTaskService;

	@Autowired
	HistoryService mHistoryService;

	@Autowired
	IdentityService mIdentityService;

	@Autowired
	ManagementService mManagementService;

	@Override
	public List queryTask(String assignee, String candidateUser, String candidateGroup, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> queryVariables(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List queryTaskHistory(String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void completeTask(String taskId, String assignee, String comment, Map<String, Object> variables,
			Map<String, Object> param) {
		Task task = mTaskService.createTaskQuery().taskId(taskId).singleResult();
		
		// add comment to the task
		if (comment != null && !comment.isEmpty()) {
			mTaskService.addComment(task.getId(), task.getProcessInstanceId(), comment);
		}

		mTaskService.setVariablesLocal(taskId, variables);
		if (!StringUtils.isBlank(assignee)) {
			mTaskService.setAssignee(taskId, assignee);
		}
		mTaskService.complete(taskId, variables);

		if (task != null) {
			param.put("isFinish", isFinishProcess(task.getProcessInstanceId()));
		}

	}

	@Override
	public void claimTask(String taskId, String assignee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFinishProcess(String processInstanceId) {
		/** 判断流程是否结束，查询正在执行的执行对象表 */
		ProcessInstance rpi = mProcessEngine.getRuntimeService()//
				.createProcessInstanceQuery()// 创建流程实例查询对象
				.processInstanceId(processInstanceId).singleResult();

		return rpi == null;
	}

	@Override
	public List queryWaitTask(int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rejectTask(String taskId, String assignee, String comment, boolean returnStart) {
		// TODO Auto-generated method stub

	}

}