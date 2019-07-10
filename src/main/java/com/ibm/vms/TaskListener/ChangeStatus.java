package com.ibm.vms.TaskListener;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value="changeStatus")
public class ChangeStatus implements JavaDelegate, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Expression status;


	@Override
	public void execute(DelegateExecution execution) {

		String statusCh = status.getExpressionText().toString();

		log.info("the status going to be changed as: {}", statusCh);
		//execution.setVariable("var1", new StringBuffer(value1).reverse().toString());

	}
	
	
}