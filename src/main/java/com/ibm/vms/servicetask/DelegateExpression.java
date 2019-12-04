package com.ibm.vms.servicetask;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class DelegateExpression  implements JavaDelegate,Serializable{
	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("请假成功");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");
		System.out.println("---------------------------------------------");

		
	}
	  
}