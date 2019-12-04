package com.ibm.vms.models;

import java.util.Date;
import java.util.Map;


import org.activiti.engine.impl.persistence.entity.*;
import org.activiti.engine.task.DelegationState;

import lombok.Data;

public class Taskmodel{

	
	

	private String TaskId;
	private String TaskName;
	private String Assignee;
	private String ProcessInstanceId;
	private String ExecutionId;
	private String ProcessDefinitionId;
	private Date createTime;
	private Date dueDate;
	private String owner;
	private String parentTaskId;
	private String description;
	private int priority;
	private String category;
	private String formKey;
	private String tenantId;
	private Date claimTime;
	private String taskDefinitionKey;
	private boolean isSuspended;
    private Map<String, Object> variables;
    
    
    

	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	public boolean isSuspended() {
		return isSuspended;
	}
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public String getTaskId() {
		return TaskId;
	}
	public void setTaskId(String taskId) {
		TaskId = taskId;
	}
	public String getTaskName() {
		return TaskName;
	}
	public void setTaskName(String taskName) {
		TaskName = taskName;
	}
	public String getAssignee() {
		return Assignee;
	}
	public void setAssignee(String assignee) {
		Assignee = assignee;
	}
	public String getProcessInstanceId() {
		return ProcessInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		ProcessInstanceId = processInstanceId;
	}
	public String getExecutionId() {
		return ExecutionId;
	}
	public void setExecutionId(String executionId) {
		ExecutionId = executionId;
	}
	public String getProcessDefinitionId() {
		return ProcessDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		ProcessDefinitionId = processDefinitionId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getParentTaskId() {
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public Date getClaimTime() {
		return claimTime;
	}
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

}
