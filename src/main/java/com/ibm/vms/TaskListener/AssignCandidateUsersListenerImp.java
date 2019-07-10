package com.ibm.vms.TaskListener;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AssignCandidateUsersListenerImp implements TaskListener, ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Expression category;
	
	private Expression group;

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
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO: fetch users from business DB
		String processtype = category.getExpressionText().toString();
		log.info("the candicate type is: {}", processtype);
		
		String processgroup = group.getExpressionText().toString();
		log.info("the candicate group is: {}", processgroup);
		
		delegateTask.setVariable("pers", Arrays.asList("1", "2"));
	}

	@Override
	public void notify(DelegateExecution execution) {
		log.info("the candicate group is: {}");
		
	}
}