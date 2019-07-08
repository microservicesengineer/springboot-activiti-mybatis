package com.ibm.vms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.vms.service.LeaveService;
import com.ibm.vms.dao.mapper.leave_infoMapper;
import com.ibm.vms.entity.db.leave_info;


@Service
public class LeaveServiceImpl implements LeaveService{
	@Autowired
	private TaskService taskService;
	@Autowired
	private leave_infoMapper leaveMapper;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public leave_info addLeaveAInfo(String msg) {
		repositoryService.createDeployment().addClasspathResource("leaveprocess.bpmn20.xml").deploy();
		//第一个参数是指定启动流程的id,即要启动哪个流程 ;第二个参数是指业务id
		//d
		System.out.println("启动前-----");
	    String id = UUID.randomUUID().toString();
	    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveprocess",id);
	    List<Task> list = taskService.createTaskQuery().taskAssignee("2").list();
	    leave_info leaveInfo = new leave_info();
		leaveInfo.setLeavemsg(msg);
		leaveInfo.setId(id);
		leaveInfo.setStatus("ing");
		leaveInfo.setTaskid(runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(id).singleResult().getId());
		leaveMapper.insert(leaveInfo);
		return leaveInfo;
	}


	@Override
	public List<leave_info> getPersonalByUserId(String userId) {
		ArrayList<leave_info> leaveInfoList = new ArrayList<>();
		List<Task> list = taskService.createTaskQuery().taskAssignee(userId).list();
		for (Task task : list) {
			ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			//获得业务流程的bussinessKey
			String businessKey = result.getBusinessKey();
			leave_info leaveInfo = leaveMapper.selectByPrimaryKey(businessKey);
			leaveInfo.setTaskid(task.getId());;
			leaveInfoList.add(leaveInfo);
		}
		return leaveInfoList;
	}

	
	@Override
	public List<leave_info> getGroupByUserId(String userId) {
		ArrayList<leave_info> leaveInfoList = new ArrayList<>();
		List<Task> list = taskService.createTaskQuery().taskCandidateUser(userId).list();
		for (Task task : list) {
			ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			//获得业务流程的bussinessKey
			String businessKey = result.getBusinessKey();
			leave_info leaveInfo = leaveMapper.selectByPrimaryKey(businessKey);
			leaveInfo.setTaskid(task.getId());;
			leaveInfoList.add(leaveInfo);
		}
		return leaveInfoList;
	}
	@Override
	public void completeTaskByUser(String taskId, String userId, String audit) {
		 Map<String, Object> map = new HashMap<>();
		 //1、认领任务
		 taskService.claim(taskId, userId);
		//2.完成任务

			 map.put("audit",audit);
			 taskService.complete(taskId, map);
		 
	}
	@Override
	public void addGroupUser(String taskId,String userId) {
		taskService.addCandidateUser(taskId, userId);
	}

	public void changeStatus(DelegateExecution execution,String status) {
		
		String key = execution.getProcessInstanceBusinessKey();
		//LeaveInfo entity = new LeaveInfo();
		leave_info entity = leaveMapper.selectByPrimaryKey(key);
		entity.setStatus(status);
		leaveMapper.updateByPrimaryKey(entity);
		
	//	System.out.println("修改请假单状态为：" + status);
		
	}


}
