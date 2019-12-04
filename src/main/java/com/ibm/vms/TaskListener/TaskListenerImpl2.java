package com.ibm.vms.TaskListener;

import javax.annotation.PostConstruct;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.vms.service.TaskFlowService;
import com.ibm.vms.service.impl.TaskFlowServiceImpl;
@Component
public class TaskListenerImpl2 implements TaskListener{
	@Autowired
	private TaskService mTaskService;;
	
	@Autowired
	public static TaskListenerImpl2 TaskListenerImpl2;  
	
	@PostConstruct
    public void init() {
		TaskListenerImpl2 = this;
		TaskListenerImpl2.mTaskService = this.mTaskService;
    }
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
//		指定组任务
//		delegateTask.addCandidateUser("3");
//		delegateTask.addCandidateUser("4");
//		System.out.println("---------------------------------------------");
//		System.out.println("---------------------------------------------");
//		System.out.println("---------------------------------------------");
//		System.out.println(delegateTask.getVariableInstances());
//		System.out.println("---------------------------------------------");
//		System.out.println("---------------------------------------------");
//		System.out.println("---------------------------------------------");
		if(delegateTask.getVariable("delegatestatus").equals(true)) {
			TaskListenerImpl2.mTaskService.delegateTask(delegateTask.getId(), delegateTask.getVariable("delegateassignee").toString());
		}
}
}