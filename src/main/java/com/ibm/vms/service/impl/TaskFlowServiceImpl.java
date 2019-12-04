package com.ibm.vms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.cmd.NeedsActiveTaskCmd;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntityManagerImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti5.engine.impl.pvm.PvmTransition;
import org.activiti5.engine.impl.pvm.process.ActivityImpl;
import org.activiti5.engine.impl.pvm.process.TransitionImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ibm.vms.models.HistoricTaskmodel;
import com.ibm.vms.models.Taskmodel;
import com.ibm.vms.service.TaskFlowService;
import com.ibm.vms.util.CommUtil;

@Service
public class TaskFlowServiceImpl implements TaskFlowService {

	@Autowired
	ProcessEngine processEngine;

	@Autowired
	RepositoryService mRepositoryService;

	@Autowired
	RuntimeService mRuntimeService;

	@Autowired
	TaskService mTaskService;

	@Autowired
	HistoryService mHistoryService;

	@Autowired
	IdentityService mIdentityService;

	@Autowired
	ManagementService mManagementService;

	@Autowired
	FormService mFormService;
	@Override
	public List queryTask(String owner, String assignee, String candidateUser, String candidateGroup, int firstResult,
			int maxResults) {

		TaskQuery taskQuery = mTaskService.createTaskQuery();

        if (!StringUtils.isBlank(owner)) {
        	taskQuery.taskOwner(owner);
        }
        
        if (!StringUtils.isBlank(assignee)) {
            taskQuery.taskAssignee(assignee);
        }

        if (!StringUtils.isBlank(candidateUser)) {
            taskQuery.taskCandidateUser(candidateUser);
        }
        
        if (!StringUtils.isBlank(candidateGroup)) {
            taskQuery.taskCandidateGroup(candidateGroup);
        }

        List<Task> tasks = taskQuery.listPage(firstResult, maxResults);
        
        List<Taskmodel> taskmodels = new ArrayList<>();
        if(tasks!=null && tasks.size()>0){
            for(Task tk:tasks){
            	Taskmodel taskmodel = new Taskmodel();
            	taskmodel.setAssignee(tk.getAssignee());
            	taskmodel.setExecutionId(tk.getExecutionId());
            	taskmodel.setProcessDefinitionId(tk.getProcessDefinitionId());
            	taskmodel.setProcessInstanceId(tk.getProcessInstanceId());;
            	taskmodel.setTaskName(tk.getName());
            	taskmodel.setTaskId(tk.getId());
            	taskmodel.setCategory(tk.getCategory());
            	taskmodel.setClaimTime(tk.getClaimTime());
            	taskmodel.setCreateTime(tk.getCreateTime());
            	taskmodel.setDueDate(tk.getDueDate());
            	taskmodel.setDescription(tk.getDescription());
            	taskmodel.setFormKey(tk.getFormKey());
            	taskmodel.setOwner(tk.getOwner());
            	taskmodel.setParentTaskId(tk.getParentTaskId());
            	taskmodel.setPriority(tk.getPriority());
            	taskmodel.setTenantId(tk.getTenantId());
            	taskmodel.setTaskDefinitionKey(tk.getTaskDefinitionKey());
            	taskmodel.setSuspended(tk.isSuspended());
            	taskmodel.setVariables(tk.getTaskLocalVariables());
            	taskmodels.add(taskmodel);
            }
        }
	
        return taskmodels;
	}
	
	@Override
	public Map<String, Object> queryVariables(String taskId) {
		// TODO Auto-generated method stub
		return mTaskService.getVariables(taskId);
	}

	@Override
	@Transactional
	public String completeTask(String taskId, String assignee, String comment, Map<String, Object> variables,
			Map<String, Object> param) {
		Task task = mTaskService.createTaskQuery().taskId(taskId).singleResult();
		if (task.getOwner() != null && !task.getOwner().equals("null")) {
	        DelegationState delegationState = task.getDelegationState();
	        if (delegationState.toString().equals("RESOLVED")) {
	            System.out.println("此委托任务已是完结状态");
	        } else if (delegationState.toString().equals("PENDING")) {
	            //如果是委托任务需要做处理
	        	param.put("delegatestatus", true);
	        	param.put("delegateassignee", assignee);
	            mTaskService.resolveTask(taskId,param);
	        }
		}else {
//			variables.put("delegatestatus", false);
			mTaskService.setVariable(taskId, "delegatestatus", false);
			mTaskService.setVariables(taskId, variables);
		}
		if (task.isSuspended()) {
			return "流程已被挂起";
		}
		// add comment to the task
		if (comment != null && !comment.isEmpty()) {
			mTaskService.addComment(task.getId(), task.getProcessInstanceId(), comment);
		}

		mTaskService.setVariablesLocal(taskId, variables);
		if (!StringUtils.isBlank(assignee)) {
			mTaskService.setAssignee(taskId, assignee);
		}
//		mTaskService.complete(taskId, variables);
//		TaskFormData taskFormData = mFormService.getTaskFormData(taskId);
//		
		Map<String,String> taskParams = variables.entrySet().stream()
			     .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
		
		mFormService.submitTaskFormData(taskId,taskParams);
		return "task已被处理";

	}

	@Override
	public void claimTask(String taskId, String assignee) {
		// TODO Auto-generated method stub		
		mTaskService.claim(taskId, assignee);

	}

	
	@Override
	public void unclaimTask(String taskId) {
		// TODO Auto-generated method stub
		mTaskService.unclaim(taskId);
	}

	
	
	@Override
	public void delegateTask(String taskId, String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		mTaskService.delegateTask(taskId, userId);
		mTaskService.resolveTask(taskId);
		param.put("delegatestatus", "true");
    	param.put("delegateassignee", userId);
		Map<String,String> taskParams = param.entrySet().stream()
			     .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
		
		mFormService.submitTaskFormData(taskId,taskParams);
	}

	@Override
	public void deleteTask(String taskId) {
		// TODO Auto-generated method stub
		mTaskService.deleteTask(taskId);
	}

	@Override
	public boolean isFinishProcess(String processInstanceId) {
		/** 判断流程是否结束，查询正在执行的执行对象表 */
		ProcessInstance rpi = processEngine.getRuntimeService()//
				.createProcessInstanceQuery()// 创建流程实例查询对象
				.processInstanceId(processInstanceId).singleResult();

		return rpi == null;
	}

	@Override
	public List queryWaitTask(int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void rejectTask(String taskId, String assignee, String comment, boolean returnStart) {
		jump(taskId, returnStart);

	}
	
	
	
    @Override
	public void withdraw(String taskId, String processId) {
		// TODO Auto-generated method stub
        Task currentTask = mTaskService.createTaskQuery().taskId(taskId).singleResult();
        BpmnModel bpmnModel = mRepositoryService.getBpmnModel(currentTask.getProcessDefinitionId());
        FlowNode targetNode = null;
    	targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(processId);
    	
    	if (targetNode == null) {
            throw new ActivitiException("开始节点不存在");
        }


        //删除当前运行任务
        String executionEntityId = mManagementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        //流程执行到来源节点
        mManagementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
	}
    
    

	public void jump(String taskId, boolean returnStart) {
        //当前任务
        Task currentTask = mTaskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程定义
//        Process process = activitiService.repositoryService.getBpmnModel(currentTask.getProcessDefinitionId()).getMainProcess();
        BpmnModel bpmnModel = mRepositoryService.getBpmnModel(currentTask.getProcessDefinitionId());


        List<HistoricActivityInstance> list = mHistoryService.createHistoricActivityInstanceQuery().processInstanceId(currentTask.getProcessInstanceId()).activityType("userTask").finished().orderByHistoricActivityInstanceEndTime().asc().list();
        if (list == null || list.size() == 0) {
            throw new ActivitiException("操作历史流程不存在");
        }

        //获取目标节点定义
        FlowNode targetNode = null;

        if (returnStart) {//驳回到发起点

//            targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(list.get(0).getActivityId());
        	targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement("usertask1");
        } else {//驳回到上一个节点

            FlowNode currNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentTask.getTaskDefinitionKey());

            for (int i = 0; i < list.size(); i++) {//倒序审核任务列表，最后一个不与当前节点相同的节点设置为目标节点
                FlowNode lastNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(list.get(i).getActivityId());
                if (list.size() > 0 && currNode.getId().equals(lastNode.getId())) {
                    targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(list.get(i - 1).getActivityId());
                    break;
                }
            }

            if (targetNode == null && list.size() > 0) {
                targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(list.get(list.size() - 1).getActivityId());
            }
        }

        if (targetNode == null) {
            throw new ActivitiException("开始节点不存在");
        }


        //删除当前运行任务
        String executionEntityId = mManagementService.executeCommand(new DeleteTaskCmd(currentTask.getId()));
        //流程执行到来源节点
        mManagementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
    }
    //删除当前运行时任务命令，并返回当前任务的执行对象id
//这里继承了NeedsActiveTaskCmd，主要时很多跳转业务场景下，要求不能时挂起任务。可以直接继承Command即可
    public class DeleteTaskCmd extends NeedsActiveTaskCmd<String> {
        public DeleteTaskCmd(String taskId) {
            super(taskId);
        }

        public String execute(CommandContext commandContext, TaskEntity currentTask) {
            //获取所需服务
            TaskEntityManagerImpl taskEntityManager = (TaskEntityManagerImpl) commandContext.getTaskEntityManager();
            //获取当前任务的来源任务及来源节点信息
            ExecutionEntity executionEntity = currentTask.getExecution();
            //删除当前任务,来源任务
            taskEntityManager.deleteTask(currentTask, "jumpReason", false, false);
            return executionEntity.getId();
        }

        public String getSuspendedTaskException() {
            return "挂起的任务不能跳转";
        }
    }

    //根据提供节点和执行对象id，进行跳转命令
    public class SetFLowNodeAndGoCmd implements Command<Void> {
        private FlowNode flowElement;
        private String executionId;

        public SetFLowNodeAndGoCmd(FlowNode flowElement, String executionId) {
            this.flowElement = flowElement;
            this.executionId = executionId;
        }

        public Void execute(CommandContext commandContext) {

            ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findById(executionId);

            //获取目标节点的来源连线
            List<SequenceFlow> flows = flowElement.getIncomingFlows();
            if (flows == null || flows.size() < 1) {

                executionEntity.setCurrentFlowElement(flowElement);
                commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);

            } else {
                //随便选一条连线来执行，时当前执行计划为，从连线流转到目标节点，实现跳转
                executionEntity.setCurrentFlowElement(flows.get(0));
            }

            commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);

            return null;
        }
    }

	@Override
	public void addCandidateUser(String taskId, String userId) {
		// TODO Auto-generated method stub
		mTaskService.addCandidateUser(taskId, userId);		
	}

	@Override
	public void getTaskCandidate(String taskId) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public List<FormProperty> getforminfo(String taskId) {
		// TODO Auto-generated method stub
		List<FormProperty> FormData = new ArrayList<FormProperty>();
		TaskFormDataImpl taskFormData = (TaskFormDataImpl) mFormService.getTaskFormData(taskId); 
		FormData.addAll(taskFormData.getFormProperties());
		return FormData;
		
	}


}