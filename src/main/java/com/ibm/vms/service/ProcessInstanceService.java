package com.ibm.vms.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

public interface ProcessInstanceService {
	
    /***
     *  开始流程实例
     * @param instanceKey 流程实例key
     * @param variables 参数
     */
    public ProcessInstance startProcessInstance(String instanceKey, String businessKey, Map<String, Object> variables);

    /***
     *  删除流程实例
     * @param instanceId 流程实例id
     */
    public void deleteProcessInstanceById(String processInstanceId);
    /***
     *  查询流程实例
     * @param instanceId 流程实例id
     */
    public void queryProcessInstanceById(String processInstanceId);
    /***
     *  查询流程实例
     * @param instanceKey 流程实例key
     */
    public void queryProcessInstanceByKey(String key);
}
