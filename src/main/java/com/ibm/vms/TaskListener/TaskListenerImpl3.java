package com.ibm.vms.TaskListener;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.vms.dao.mapper.order_infoMapper;
import com.ibm.vms.entity.db.order_info;
import com.ibm.vms.servicetask.ServiceTask;
@Component
public class TaskListenerImpl3 implements ExecutionListener{
	
	@Autowired
	private order_infoMapper order;
	@Autowired	
	public static TaskListenerImpl3 TaskListenerImpl3;  // 关键2

	@PostConstruct
    public void init() {
		TaskListenerImpl3 = this;
		TaskListenerImpl3.order = this.order;
    }
	
	@Override
	public void notify(DelegateExecution execution) {		
		String id = execution.getProcessInstanceBusinessKey();
		order_info entity = new order_info();
		entity = TaskListenerImpl3.order.selectByPrimaryKey(id);
		entity.setStatus("process");
		TaskListenerImpl3.order.updateByPrimaryKey(entity);
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("目前状态为process");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");	
}

}
