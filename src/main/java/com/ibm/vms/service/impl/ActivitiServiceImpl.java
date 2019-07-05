package com.ibm.vms.service.impl;

import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}