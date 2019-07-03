package com.ibm.vms.TaskListener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskListenerImpl implements TaskListener{
	@Override
	public void notify(DelegateTask delegateTask) {
		//指定组任务
		delegateTask.addCandidateUser("1");
		delegateTask.addCandidateUser("2");

}
}