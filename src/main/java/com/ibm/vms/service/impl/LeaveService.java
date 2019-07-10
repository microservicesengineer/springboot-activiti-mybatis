package com.ibm.vms.service.impl;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.vms.dao.mapper.leave_infoMapper;

import lombok.extern.slf4j.Slf4j;

@Service(value = "leaveService")
@Slf4j
public class LeaveService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private leave_infoMapper leaveMapper;
	
	public void changeStatus(DelegateExecution execution, String status) {
		String key = execution.getProcessInstanceBusinessKey();
		
		log.info("the status is going to be changed as: {}", key);
	}
	
}
