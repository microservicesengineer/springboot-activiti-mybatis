package com.ibm.vms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.persistence.entity.HistoricDetailEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.vms.dao.mapper.order_infoMapper;
import com.ibm.vms.entity.db.order_info;
import com.ibm.vms.models.Formmodel;
import com.ibm.vms.models.HistoricTaskmodel;
import com.ibm.vms.models.Instancemodel;
import com.ibm.vms.models.Taskmodel;
import com.ibm.vms.service.ProcessInstanceService;
import com.ibm.vms.servicetask.DelegateExpression;
import com.ibm.vms.servicetask.ServiceTask;

@Service
public class ProcessInstanceServiceImpl implements ProcessInstanceService {
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
	
	@Autowired
	private order_infoMapper orderMapper;

	@Autowired
	FormService mFormService;
	/***
	 * 开始流程
	 * 
	 * @param instanceKey 流程实例key
	 * @param variables  参数
	 */
	public Instancemodel startProcessInstance(String instanceKey, String businessKey, Map<String, Object> variables) {
		ProcessInstance processInstance = null;
	    order_info orderInfo = new order_info();
	    if (orderMapper.selectByPrimaryKey(businessKey) != null) {
	    	orderInfo.setId(businessKey);
	    	orderMapper.insert(orderInfo);
	    }
		if (businessKey != null) {
		    variables.put("serviceTask", new ServiceTask());
		    variables.put("myDelegate", new DelegateExpression());
			processInstance = runtimeService.startProcessInstanceByKey(instanceKey, businessKey, variables);
		} else {
			processInstance = runtimeService.startProcessInstanceByKey(instanceKey, variables);
		}
       	Instancemodel im = new Instancemodel();
    	im.setActivityId(processInstance.getActivityId());
    	im.setBusinessKey(processInstance.getBusinessKey());
    	im.setDeploymentId(processInstance.getDeploymentId());
    	im.setDescription(processInstance.getDescription());
    	im.setLocalizedDescription(processInstance.getLocalizedDescription());
    	im.setLocalizedName(processInstance.getLocalizedName());
    	im.setName(processInstance.getName());
    	im.setParentId(processInstance.getParentId());
    	im.setProcessDefinitionId(processInstance.getProcessDefinitionId());
    	im.setProcessDefinitionKey(processInstance.getProcessDefinitionKey());
    	im.setProcessDefinitionName(processInstance.getProcessDefinitionName());
    	im.setProcessDefinitionVersion(processInstance.getProcessDefinitionVersion());
    	im.setProcessInstanceId(processInstance.getProcessInstanceId());
    	im.setRootProcessInstanceId(processInstance.getRootProcessInstanceId());
    	im.setStartTime(processInstance.getStartTime());
    	im.setStartUserId(processInstance.getStartUserId());
    	im.setSuperExecutionId(processInstance.getSuperExecutionId());
    	im.setTenantId(processInstance.getTenantId());
		return im;


	}

	/***
	 * 删除流程实例
	 * 
	 * @param instanceKey
	 *            流程实例key
	 * @param variables
	 *            参数
	 */
	public void deleteProcessInstanceById(String processInstanceId) {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		if (pi == null) {
			// 该流程实例已经完成了
			historyService.deleteHistoricProcessInstance(processInstanceId);
		} else {
			// 该流程实例未结束的
			runtimeService.deleteProcessInstance(processInstanceId, "");
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
	}

	/***
	 * 查询流程实例
	 * 
	 * @param instanceKey
	 *            流程实例key
	 * @param variables
	 *            参数
	 */
	public List<Formmodel> queryProcessInstanceById(String processInstanceId) {
//		ProcessInstance pi = processEngine.getRuntimeService()// 表示正在执行的流程实例和执行对象
//				.createProcessInstanceQuery()// 创建流程实例查询
//				.processInstanceId(processInstanceId)// 使用流程实例ID查询
//				.singleResult();
//		if (pi == null) {
//			return "该流程实例走完";
//		} else {
//			return "该流程实例还没走完";
//		}
        List<HistoricDetail> list = historyService
                .createHistoricDetailQuery()
                .formProperties()
                .processInstanceId(processInstanceId)
                .list();
        List<Formmodel> list2 = new ArrayList<Formmodel>();
        if (list != null && list.size() > 0) {
            for (HistoricDetail hd : list) {
            	HistoricFormProperty field = (HistoricFormProperty) hd;
            	HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(field.getTaskId()).singleResult();
            	Formmodel FM = new Formmodel();
            	FM.setPropertyId(field.getPropertyId());
            	FM.setPropertyValue(field.getPropertyValue());
            	FM.setAssignee(task.getAssignee());
            	list2.add(FM);
            }
            }
		return list2;
	}

	/***
	 * 查询流程实例
	 * 
	 * @param instanceKey
	 *            流程实例key
	 */
	public List queryProcessInstanceByKey(String processDefinitionKey) {
		List<ProcessInstance> ProcessInstanceList = processEngine.getRuntimeService().createProcessInstanceQuery()
				.processDefinitionKey(processDefinitionKey).orderByProcessDefinitionKey().desc().list();
		List<Instancemodel> instancemodels = new ArrayList<>();
        if(ProcessInstanceList!=null && ProcessInstanceList.size()>0){
            for(ProcessInstance pi:ProcessInstanceList){
            	Instancemodel im = new Instancemodel();
            	im.setActivityId(pi.getActivityId());
            	im.setBusinessKey(pi.getBusinessKey());
            	im.setDeploymentId(pi.getDeploymentId());
            	im.setDescription(pi.getDescription());
            	im.setLocalizedDescription(pi.getLocalizedDescription());
            	im.setLocalizedName(pi.getLocalizedName());
            	im.setName(pi.getName());
            	im.setParentId(pi.getParentId());
            	im.setProcessDefinitionId(pi.getProcessDefinitionId());
            	im.setProcessDefinitionKey(pi.getProcessDefinitionKey());
            	im.setProcessDefinitionName(pi.getProcessDefinitionName());
            	im.setProcessDefinitionVersion(pi.getProcessDefinitionVersion());
            	im.setProcessInstanceId(pi.getProcessInstanceId());
            	im.setRootProcessInstanceId(pi.getRootProcessInstanceId());
            	im.setStartTime(pi.getStartTime());
            	im.setStartUserId(pi.getStartUserId());
            	im.setSuperExecutionId(pi.getSuperExecutionId());
            	im.setTenantId(pi.getTenantId());
//            	pi.getId();
            	instancemodels.add(im);
            }
            }
		return instancemodels;
	}

	@Override
	public String changeInstanctStatusById(String processInstanceId, Integer status) {
		// TODO Auto-generated method stub
		if (status.equals(1)) {
			runtimeService.suspendProcessInstanceById(processInstanceId);
			return "操作成功，流程已挂起";
		}else if (status.equals(0)) {
			runtimeService.activateProcessInstanceById(processInstanceId);
			return "操作成功，流程已激活";
		}else {
			return "操作失败";
		}
	}

	@Override
	public Map<String,Object> getVariablesById(String processInstanceId) {
		// TODO Auto-generated method stub
		ProcessInstance pi = processEngine.getRuntimeService()// 表示正在执行的流程实例和执行对象
				.createProcessInstanceQuery()// 创建流程实例查询
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.singleResult();
		Map<String,Object> var =pi.getProcessVariables();
		
		return var;
	}
	
	@Override
	public List queryTaskHistory(String processInstanceId) {
		// TODO Auto-generated method stub
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                .processInstanceId(processInstanceId)//
                .orderByHistoricTaskInstanceStartTime().asc()
                .list();
       List<HistoricTaskmodel> list1 = new ArrayList<>();;
       
       if (list != null && list.size() > 0) {
           for (HistoricTaskInstance hti : list) {
        	   HistoricTaskmodel htm = new HistoricTaskmodel();
        	   htm.setTaskId(hti.getId());
        	   htm.setAssignee(hti.getAssignee());
        	   htm.setCategory(hti.getCategory());
        	   htm.setClaimTime(hti.getClaimTime());
        	   htm.setDescription(hti.getDescription());
        	   htm.setDueDate(hti.getDueDate());
        	   htm.setExecutionId(hti.getExecutionId());
        	   htm.setFormKey(hti.getFormKey());
        	   htm.setName(hti.getName());
        	   htm.setOwner(hti.getOwner());
        	   htm.setParentTaskId(hti.getParentTaskId());
        	   htm.setPriority(hti.getPriority());
        	   htm.setTaskDefinitionKey(hti.getTaskDefinitionKey());
        	   htm.setTenantId(hti.getTenantId());

               List<HistoricVariableInstance> list2 = historyService.createHistoricVariableInstanceQuery().taskId(hti.getId()).list();
               if (list2 != null && list2.size() > 0) {
                       Map<String, Object> variables = new HashMap<>();
                       for (HistoricVariableInstance historicVariableInstance : list2) {
                           variables.put(historicVariableInstance.getVariableName(), historicVariableInstance.getValue());
                       }
                       htm.setVariables(variables);
                   }              
               list1.add(htm);
           }
           }
       return list1;
    }	
}
