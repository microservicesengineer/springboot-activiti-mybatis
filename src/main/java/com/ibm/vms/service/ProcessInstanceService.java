package com.ibm.vms.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.ibm.vms.models.Formmodel;
import com.ibm.vms.models.Instancemodel;

public interface ProcessInstanceService {
	
    /***
     *  开始流程实例
     * @param instanceKey 流程实例key
     * @param variables 参数
     */
    public Instancemodel startProcessInstance(String instanceKey, String businessKey, Map<String, Object> variables);

    /***
     *  删除流程实例
     * @param instanceId 流程实例id
     */
    public void deleteProcessInstanceById(String processInstanceId);
    /***
     *  查询流程实例
     * @param instanceId 流程实例id
     */
    public List<Formmodel> queryProcessInstanceById(String processInstanceId);
    /***
     *  查询流程实例
     * @param instanceKey 流程实例key
     */
    public List queryProcessInstanceByKey(String processDefinitionKey);
    /***
     *  挂起 / 激活实例
     * @param processInstanceId 流程实例id
     * @param status 1挂起 0激活
     */
    public String changeInstanctStatusById(String processInstanceId,Integer status);
    /***
     *  查询流程实例
     * @param instanceId 流程实例id
     */
    public Map<String,Object> getVariablesById(String processInstanceId);
    /***
     * 根据实例ID查询任务审核历史
     * @param processInstanceId 实例ID
     * @return
     */
    public List queryTaskHistory(String processInstanceId);
   
}
