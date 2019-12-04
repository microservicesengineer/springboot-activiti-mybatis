package com.ibm.vms.servicetask;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.vms.dao.mapper.order_infoMapper;
import com.ibm.vms.entity.db.order_info;

@Component
public class ServiceTask implements Serializable{
	@Autowired
	private order_infoMapper order;
	@Autowired	
	public static ServiceTask serviceTask;  // 关键2

	@PostConstruct
    public void init() {
		serviceTask = this;
		serviceTask.order = this.order;
    }
	

	public void changeStatus(DelegateExecution execution,String status) {
		String id = execution.getProcessInstanceBusinessKey();
		order_info entity = new order_info();
		if (serviceTask.order.selectByPrimaryKey(id)==null) {
			entity.setStatus(status);
			entity.setId(id);
			serviceTask.order.insert(entity);
		}else {
			entity = serviceTask.order.selectByPrimaryKey(id);
			entity.setStatus(status);
			serviceTask.order.updateByPrimaryKey(entity);
		}
		if(status.equals("new")) {
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("目前状态为new");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
		}else if(status.equals("reject")){
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("目前状态为reject");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
		}else if(status.equals("approved")) {
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("目前状态为approved");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");			
		}else if(status.equals("process")) {
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("目前状态为process");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");			
		}else if(status.equals("waiting process")) {
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("目前状态为waiting process");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");
			System.out.println("---------------------------------------------");			
		}
}
	public void autoDelegate(DelegateExecution execution) {
		System.out.println(execution.getParentId());
		System.out.println(execution.getCurrentActivityId());	
		System.out.println(execution.getId());
	}
}
