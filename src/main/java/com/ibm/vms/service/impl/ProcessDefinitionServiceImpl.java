package com.ibm.vms.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.vms.models.ProcessDefinitionmodel;
import com.ibm.vms.service.ProcessDefinitionService;

@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
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
	FormService mFormService;

	/**
	 * deploy with category
	 *
	 * @param bpmnName
	 */

	public void deploy(String bpmnName, String category) {

		String bpmn = "processes/" + bpmnName + ".bpmn20.xml";
		repositoryService.createDeployment().name(bpmnName)// deploy name
		.addClasspathResource(bpmn)
		.category(category) // ad																									// category
		.deploy();// deploy
	}

	@Override
	public void deploy(InputStream fileInputStream, String fileName, String category) {
		ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
		repositoryService.createDeployment().addZipInputStream(zipInputStream).name(fileName).category(category)
				.deploy();

	}

	public List<ProcessDefinitionmodel> deploymentQuery() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
		List<ProcessDefinitionmodel> list1 = new ArrayList<>();
		if(list!=null && list.size()>0){
            for(ProcessDefinition pd:list){
            	ProcessDefinitionmodel pdm =new ProcessDefinitionmodel();
        		pdm.setCategory(pd.getCategory());
        		pdm.setDeploymentId(pd.getDeploymentId());
        		pdm.setDescription(pd.getDescription());
        		pdm.setDiagramResourceName(pd.getDiagramResourceName());
        		pdm.setGraphicalNotationDefined(pd.hasGraphicalNotation());
        		pdm.setHasStartFormKey(pd.hasStartFormKey());
        		pdm.setKey(pdm.getKey());
        		pdm.setName(pd.getName());
        		pdm.setResourceName(pd.getResourceName());
        		pdm.setTenantId(pd.getTenantId());
        		pdm.setVersion(pd.getVersion());
        		list1.add(pdm);
            }
		}
		return list1;
	}

	/***
	 * 根据部署ID查询部署
	 */
	public ProcessDefinitionmodel deploymentQuerybyid(String deploymentId) {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
		ProcessDefinitionmodel pdm =new ProcessDefinitionmodel();
		pdm.setCategory(pd.getCategory());
		pdm.setDeploymentId(pd.getDeploymentId());
		pdm.setDescription(pd.getDescription());
		pdm.setDiagramResourceName(pd.getDiagramResourceName());
		pdm.setGraphicalNotationDefined(pd.hasGraphicalNotation());
		pdm.setHasStartFormKey(pd.hasStartFormKey());
		pdm.setKey(pdm.getKey());
		pdm.setName(pd.getName());
		pdm.setResourceName(pd.getResourceName());
		pdm.setTenantId(pd.getTenantId());
		pdm.setVersion(pd.getVersion());
		
		
		// 获取表单key和启动节点表单数据
		String formKey = mFormService.getStartFormKey(pd.getId());
		StartFormData startFormData = mFormService.getStartFormData(pd.getId());
		List<FormProperty> formPropertyList = startFormData.getFormProperties();
		
		
//		Map<String,String> properties = new HashMap<String,String>();
//		properties.put("startDate","2019-01-01");
//		properties.put("endDate","2019-01-02");
//		properties.put("reason","hello world");
//		mFormService.submitStartFormData(pd.getId(),properties);

		return pdm;
	}

	/***
	 * 根据部署ID删除部署
	 */
	public void deleteDeploymentbyid(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	@Override
	public List queryrecalltask(String deploymentId) {
		// TODO Auto-generated method stu
		List recalltask= new ArrayList<>();
		String processDefinitionId = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult().getId();
			BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
			if(model != null) {
			    Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
			    for(FlowElement e : flowElements) {
			    	if(e.getDocumentation()!= null &&e.getDocumentation().equals("recall")) {
			    	Map<String, Object> var = new HashMap<>();
			    	var.put("flowelement id",e.getId());
			    	var.put("name",e.getName());
			        System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName());
			        recalltask.add(var);
			    }
			    }
			
		}
		return recalltask;
	}
	
}