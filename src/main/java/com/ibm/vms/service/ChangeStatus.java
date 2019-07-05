package com.ibm.vms.service;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
@Service(value="changeStatus")
public class ChangeStatus  implements JavaDelegate,Serializable{
 
	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("请假单状态变更");
		
	}
	  
}
