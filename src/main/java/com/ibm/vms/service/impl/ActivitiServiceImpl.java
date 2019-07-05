package com.ibm.vms.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti6.demo.service.impl.ActivitiServiceImpl;
import com.activiti6.demo.service.impl.ActivitiServiceImpl.DeleteTaskCmd;
import com.activiti6.demo.service.impl.ActivitiServiceImpl.SetFLowNodeAndGoCmd;
import com.ibm.vms.service.ActivitiService;



@Service
public class ActivitiServiceImpl implements ActivitiService{
    @Autowired
    ProcessEngine processEngine;

    @Autowired
    RepositoryService repositoryService;


    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    IdentityService identityService;

    @Autowired
    ManagementService managementService;
    /**
     * 发布规则文件
     *
     * @param bpmnName
     */
    public void deploy(String bpmnName, String category) {

        String bpmn = "processes/" + bpmnName + ".bpmn";
        
        repositoryService.createDeployment()//创建一个部署对象
                .name(bpmnName)//添加部署的名称
                .addInputStream(bpmn, this.getClass().getClassLoader().getResourceAsStream(bpmn))
                .category(category) // 添加类别
                .deploy();//完成部署
    }
    public List<Deployment> deploymentQuery() {
    	List<Deployment> list = repositoryService.createDeploymentQuery().list();
    return list;
    }
    /***
     * 根据部署ID查询部署
     */
    public Deployment deploymentQuerybyid(String deploymentId) {
    	Deployment Deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    	
    	return Deployment;
    }
    /***
     * 根据部署ID删除部署
     */
    public void deleteDeploymentbyid(String deploymentId) {
    	repositoryService.deleteDeployment(deploymentId,true); 
    }
    /***
     *  开始流程
     * @param instanceKey 流程实例key
     * @param variables 参数
     */
    public ProcessInstance startProcessInstance(String instanceKey, String businessKey, Map<String, Object> variables) {
    	ProcessInstance processInstance = null;
    	if (businessKey != null) {
    		processInstance = runtimeService.startProcessInstanceByKey(instanceKey, businessKey, variables);
    	} else {
            processInstance = runtimeService.startProcessInstanceByKey(instanceKey, variables);
    	}
        return processInstance;
    }
    /***
     *  删除流程实例
     * @param instanceKey 流程实例key
     * @param variables 参数
     */
    public void deleteProcessInstanceById(String processInstanceId) {
    	ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    	if(pi==null){
    			//该流程实例已经完成了
    		historyService.deleteHistoricProcessInstance(processInstanceId);
    	}else{
    			//该流程实例未结束的
    		runtimeService.deleteProcessInstance(processInstanceId,"");
    		historyService.deleteHistoricProcessInstance(processInstanceId);
    	}
    }
    
    /***
     *  查询流程实例
     * @param instanceKey 流程实例key
     * @param variables 参数
     */
    public void queryProcessInstanceById(String processInstanceId) {
    	 ProcessInstance pi = processEngine.getRuntimeService()//表示正在执行的流程实例和执行对象
                 .createProcessInstanceQuery()//创建流程实例查询
                 .processInstanceId(processInstanceId)//使用流程实例ID查询
                 .singleResult();
         if(pi==null){
             System.out.println("该流程实例走完");
         }
         else{
             System.out.println("该流程实例还没走完");
         }
    }
    /***
     *  查询流程实例
     * @param instanceKey 流程实例key
     */
    public void queryProcessInstanceByKey(String businessKey) {
    	 ProcessInstance pi = processEngine.getRuntimeService()
    			 .createProcessInstanceQuery()
    			 .processInstanceBusinessKey(businessKey)
    			 .singleResult();
         if(pi==null){
             System.out.println("该流程实例走完");
         }
         else{
             System.out.println("该流程实例还没走完");
         }
    }
    
    /***
     *  查询历史Taskdata
     * @param instanceKey 流程实例key
     */
    public void queryTaskHistoricalDataByID(String processInstanceId) {
    	List<HistoricTaskInstance> list = processEngine.getHistoryService()
				.createHistoricTaskInstanceQuery()
				.processInstanceId(processInstanceId)
				.list();
		if(list!=null && list.size()>0){
			for(HistoricTaskInstance hti:list){
				System.out.println(hti.getId()+"    "+hti.getName()+"   "+hti.getClaimTime());
			}
		}
    }
    /****
     * 任务指派
     * @param taskId
     * @param assignee
     */
    public void claimTask(String taskId, String assignee) {
    	taskService.claim(taskId, assignee);
    }
    @Override
    public boolean isFinishProcess(String processInstanceId) {

        /**判断流程是否结束，查询正在执行的执行对象表*/
        ProcessInstance rpi = processEngine.getRuntimeService()//
                .createProcessInstanceQuery()//创建流程实例查询对象
                .processInstanceId(processInstanceId)
                .singleResult();

        return rpi == null;
    }
    @Override
    public void completeTask(String taskId, String assignee, Map<String, Object> variables, Map<String, Object> param) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        //完成请假申请任务
        taskService.setVariablesLocal(taskId, variables);
        taskService.complete(taskId, variables);

        if (task != null) {
            param.put("isFinish", isFinishProcess(task.getProcessInstanceId()));
        }
    }
    @Override
    public void rejectTask(String taskId, String assignee, boolean returnStart) {
        jump(this, taskId, assignee, returnStart);
    }
    //跳转方法
    public void jump(ActivitiServiceImpl activitiService, String taskId, String assignee, boolean returnStart) {
        //当前任务
        Task currentTask = activitiService.taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程定义
//        Process process = activitiService.repositoryService.getBpmnModel(currentTask.getProcessDefinitionId()).getMainProcess();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId());


        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(currentTask.getProcessInstanceId()).activityType("userTask").finished().orderByHistoricActivityInstanceEndTime().asc().list();
        if (list == null || list.size() == 0) {
            throw new ActivitiException("操作历史流程不存在");
        }

        //获取目标节点定义
        FlowNode targetNode = null;

        if (returnStart) {//驳回到发起点

            targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(list.get(0).getActivityId());
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


//            Map<String, Object> flowElementMap = new TreeMap<>();
//            Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
//            for (FlowElement flowElement : flowElements) {
//
//                flowElementMap.put(flowElement.getId(), flowElement);
//            }
//
//
//
//            targetNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(tmplist.get(tmplist.size() - 1).getActivityId());

        }

        if (targetNode == null) {
            throw new ActivitiException("开始节点不存在");
        }


        //删除当前运行任务
        String executionEntityId = activitiService.managementService.executeCommand(activitiService.new DeleteTaskCmd(currentTask.getId()));
        //流程执行到来源节点
        activitiService.managementService.executeCommand(activitiService.new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
    }
}