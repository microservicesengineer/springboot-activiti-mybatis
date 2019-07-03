package com.ibm.vms.delegate;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
@Service
public class MyDelegate  implements JavaDelegate,Serializable{
 
	@Override
	public void execute(DelegateExecution execution) {
		System.out.println("修改请假单");
		
	}
	  
}
