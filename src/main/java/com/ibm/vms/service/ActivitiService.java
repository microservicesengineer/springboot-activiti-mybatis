package com.ibm.vms.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

import com.ibm.vms.service.impl.ActivitiServiceImpl;

public interface ActivitiService {
    /***
     * 发布规则
     * @param bpmnName
     */
    public void deploy(String bpmnName, String category);
    /***
     * 查询部署列表
     */
    public List<Deployment> deploymentQuery();
    /***
     * 根据部署ID查询部署
     */
    public Deployment deploymentQuerybyid(String deploymentId);
    /***
     * 根据部署ID删除部署
     */
    public void deleteDeploymentbyid(String deploymentId);
    
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
    /***
     *  查询历史Taskdata
     * @param instanceKey 流程实例key
     */
    public void queryTaskHistoricalDataByID(String processInstanceId);
    /****
     * 任务指派
     * @param taskId
     * @param assignee
     */
    public void claimTask(String taskId, String assignee);
    /****
     * 判断流程是否完成
     * @param processInstanceId
     */
    public boolean isFinishProcess(String processInstanceId);
    /***
     * 完成任务
     * @param taskId 任务id
     * @param assignee 分配到任务的人
     * @param variables 表单参数信息
     * @param param 返回值
     */
    public void completeTask(String taskId, String assignee, Map<String, Object> variables, Map<String, Object> param);
    /***
     * 驳回任务
     * @param taskId 当前节点
     * @param returnStart 是否返回到起点
     */
    public void rejectTask(String taskId, String assignee, boolean returnStart);
    public void jump(ActivitiServiceImpl activitiService, String taskId, String assignee, boolean returnStart);
}
