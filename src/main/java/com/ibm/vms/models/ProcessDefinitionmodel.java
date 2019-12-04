package com.ibm.vms.models;

import java.util.Map;


public class ProcessDefinitionmodel {
	
	  private String name;
	  private String description;
	  private String key;
	  private int version;
	  private String category;
	  private String deploymentId;
	  private String resourceName;
	  private String tenantId;
	  private String diagramResourceName;
	  private boolean isGraphicalNotationDefined;
	  private boolean hasStartFormKey;
	  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getDiagramResourceName() {
		return diagramResourceName;
	}
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}
	public boolean isGraphicalNotationDefined() {
		return isGraphicalNotationDefined;
	}
	public void setGraphicalNotationDefined(boolean isGraphicalNotationDefined) {
		this.isGraphicalNotationDefined = isGraphicalNotationDefined;
	}

	public boolean isHasStartFormKey() {
		return hasStartFormKey;
	}
	public void setHasStartFormKey(boolean hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}



}
