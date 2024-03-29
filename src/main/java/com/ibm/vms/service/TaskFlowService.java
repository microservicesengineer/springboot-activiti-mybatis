package com.ibm.vms.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.form.TaskFormDataImpl;

public interface TaskFlowService {
	
	 /***
     * 查询用户任务列表
     * @param assignee 任务执行人
     * @param candidateUser 候选用户
     * @param candidateGroup 候选用户组
     * @return
     */
    public List queryTask(String owner, String assignee, String candidateUser, String candidateGroup, int firstResult, int maxResults);


    /***
     * 查询任务参数
     * @param taskId
     * @return
     */
    public Map<String, Object> queryVariables(String taskId);


    /***
     * 完成任务
     * @param taskId 任务id
     * @param assignee 分配到任务的人
     * @param variables 表单参数信息
     * @param param 返回值
     */
    public String completeTask(String taskId, String assignee, String comment, Map<String, Object> variables, Map<String, Object> param);

    /****
     * 任务指派
     * @param taskId
     * @param assignee
     */
    public void claimTask(String taskId, String assignee);

    /****
     * 任务指派
     * @param taskId
     * @param assignee
     */
    public void unclaimTask(String taskId);
    
    public void deleteTask(String taskId);


    /****
     * 流程是否完成
     * @param processInstanceId
     * @return
     */
    public boolean isFinishProcess(String processInstanceId);


    /***
     * 查询待办任务
     * @param firstResult 开始位置
     * @param maxResults 最大记录数
     * @return
     */
    public List queryWaitTask(int firstResult, int maxResults);

    /***
     * 驳回任务
     * @param taskId 当前节点
     * @param returnStart 是否返回到起点
     */
    public void rejectTask(String taskId, String assignee, String comment, boolean returnStart);
    /***
     * 转签任务
     * @param taskId 当前节点
     * @param userid 转签对象
     */
    public void delegateTask(String taskId, String userId);
    /***
     * 添加CandidateUser
     * @param taskId 当前节点
     * @param userid CandidateUser对象
     */
    public void addCandidateUser(String taskId, String userId);
    /***
     * 查看CandidateUsers
     * @param taskId 当前节点

     */
    public void getTaskCandidate(String taskId);
    
    public void withdraw(String taskId,String processId);
    
    public List<FormProperty> getforminfo(String taskId);
}
