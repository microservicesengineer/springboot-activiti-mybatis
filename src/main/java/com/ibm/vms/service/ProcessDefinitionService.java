package com.ibm.vms.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

import com.ibm.vms.service.impl.ProcessDefinitionServiceImpl;

public interface ProcessDefinitionService {
	
	
    public void deploy(InputStream fileInputStream, String fileName, String category);

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
}
