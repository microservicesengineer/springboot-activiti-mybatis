package com.ibm.vms.TaskListener;

import java.io.Serializable;
import java.util.Arrays;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AssignCandidateUsersListenerImp implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Expression category;

	private Expression type;

	public enum PROCESS_CATEGORY {

		LEAVE("LEAVE");

		private String name;

		private PROCESS_CATEGORY(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

//	 @Override
//	 public void notify(DelegateTask delegateTask) {
//	log.info("the event in task listener type is: {}",  delegateTask.getEventName());
//	 // TODO: fetch users from business DB
//	 String processtype = category.getExpressionText().toString();
//	 log.info("the candicate type is: {}", processtype);
//	
//	 String processgroup = type.getExpressionText().toString();
//	 log.info("the candicate type is: {}", processgroup);
//	
//	// delegateTask.setVariable("pers", Arrays.asList("1", "2"));
//	 }

//	@Override
//	public void execute(DelegateExecution execution) {
//		log.info("the event type in execution is: {}",  execution.getEventName());
//		
//		
//		String processtype = category.getExpressionText().toString();
//		log.info("the candicate type is: {}", processtype);
//
//		String processgroup = type.getExpressionText().toString();
//		log.info("the candicate type is: {}", processgroup);
//
//		execution.setVariable("pers", Arrays.asList("1", "2"));
//		
//	}

	@Override
	public void notify(DelegateExecution execution) {
		
		log.info("the event type in execution is: {}",  execution.getEventName());
		
		
		String processtype = category.getExpressionText().toString();
		log.info("the candicate type is: {}", processtype);

		String processgroup = type.getExpressionText().toString();
		log.info("the candicate type is: {}", processgroup);

		execution.setVariable("pers", Arrays.asList("1", "2"));

	}
}