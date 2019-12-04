package com.ibm.vms.TaskListener;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class TaskListenerImpl1 implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) {
		//指定组任务
		execution.setVariable("pers", Arrays.asList("1", "2"));

}
}