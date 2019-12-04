package com.ibm.vms.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProcessInstanceEntity
 */
@Validated

public class ProcessInstanceEntity  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("processDefinitionId")
  private Integer processDefinitionId = null;

  @JsonProperty("processDefinitionName")
  private String processDefinitionName = null;

  @JsonProperty("processDefinitionKey")
  private String processDefinitionKey = null;

  @JsonProperty("processDefinitionVersion")
  private Integer processDefinitionVersion = null;

  @JsonProperty("deploymentId")
  private String deploymentId = null;

  @JsonProperty("businessKey")
  private String businessKey = null;

  @JsonProperty("isSuspended")
  private Boolean isSuspended = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("startUserId")
  private String startUserId = null;

  public ProcessInstanceEntity processDefinitionId(Integer processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

  /**
   * Get processDefinitionId
   * @return processDefinitionId
  **/
  @ApiModelProperty(value = "")


  public Integer getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(Integer processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public ProcessInstanceEntity processDefinitionName(String processDefinitionName) {
    this.processDefinitionName = processDefinitionName;
    return this;
  }

  /**
   * Get processDefinitionName
   * @return processDefinitionName
  **/
  @ApiModelProperty(value = "")


  public String getProcessDefinitionName() {
    return processDefinitionName;
  }

  public void setProcessDefinitionName(String processDefinitionName) {
    this.processDefinitionName = processDefinitionName;
  }

  public ProcessInstanceEntity processDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
    return this;
  }

  /**
   * Get processDefinitionKey
   * @return processDefinitionKey
  **/
  @ApiModelProperty(value = "")


  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }

  public ProcessInstanceEntity processDefinitionVersion(Integer processDefinitionVersion) {
    this.processDefinitionVersion = processDefinitionVersion;
    return this;
  }

  /**
   * Get processDefinitionVersion
   * @return processDefinitionVersion
  **/
  @ApiModelProperty(value = "")


  public Integer getProcessDefinitionVersion() {
    return processDefinitionVersion;
  }

  public void setProcessDefinitionVersion(Integer processDefinitionVersion) {
    this.processDefinitionVersion = processDefinitionVersion;
  }

  public ProcessInstanceEntity deploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
    return this;
  }

  /**
   * Get deploymentId
   * @return deploymentId
  **/
  @ApiModelProperty(value = "")


  public String getDeploymentId() {
    return deploymentId;
  }

  public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
  }

  public ProcessInstanceEntity businessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  /**
   * Get businessKey
   * @return businessKey
  **/
  @ApiModelProperty(value = "")


  public String getBusinessKey() {
    return businessKey;
  }

  public void setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
  }

  public ProcessInstanceEntity isSuspended(Boolean isSuspended) {
    this.isSuspended = isSuspended;
    return this;
  }

  /**
   * Get isSuspended
   * @return isSuspended
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsSuspended() {
    return isSuspended;
  }

  public void setIsSuspended(Boolean isSuspended) {
    this.isSuspended = isSuspended;
  }

  public ProcessInstanceEntity tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Get tenantId
   * @return tenantId
  **/
  @ApiModelProperty(value = "")


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public ProcessInstanceEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProcessInstanceEntity startUserId(String startUserId) {
    this.startUserId = startUserId;
    return this;
  }

  /**
   * Get startUserId
   * @return startUserId
  **/
  @ApiModelProperty(value = "")


  public String getStartUserId() {
    return startUserId;
  }

  public void setStartUserId(String startUserId) {
    this.startUserId = startUserId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProcessInstanceEntity processInstanceEntity = (ProcessInstanceEntity) o;
    return Objects.equals(this.processDefinitionId, processInstanceEntity.processDefinitionId) &&
        Objects.equals(this.processDefinitionName, processInstanceEntity.processDefinitionName) &&
        Objects.equals(this.processDefinitionKey, processInstanceEntity.processDefinitionKey) &&
        Objects.equals(this.processDefinitionVersion, processInstanceEntity.processDefinitionVersion) &&
        Objects.equals(this.deploymentId, processInstanceEntity.deploymentId) &&
        Objects.equals(this.businessKey, processInstanceEntity.businessKey) &&
        Objects.equals(this.isSuspended, processInstanceEntity.isSuspended) &&
        Objects.equals(this.tenantId, processInstanceEntity.tenantId) &&
        Objects.equals(this.name, processInstanceEntity.name) &&
        Objects.equals(this.startUserId, processInstanceEntity.startUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processDefinitionId, processDefinitionName, processDefinitionKey, processDefinitionVersion, deploymentId, businessKey, isSuspended, tenantId, name, startUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProcessInstanceEntity {\n");
    
    sb.append("    processDefinitionId: ").append(toIndentedString(processDefinitionId)).append("\n");
    sb.append("    processDefinitionName: ").append(toIndentedString(processDefinitionName)).append("\n");
    sb.append("    processDefinitionKey: ").append(toIndentedString(processDefinitionKey)).append("\n");
    sb.append("    processDefinitionVersion: ").append(toIndentedString(processDefinitionVersion)).append("\n");
    sb.append("    deploymentId: ").append(toIndentedString(deploymentId)).append("\n");
    sb.append("    businessKey: ").append(toIndentedString(businessKey)).append("\n");
    sb.append("    isSuspended: ").append(toIndentedString(isSuspended)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    startUserId: ").append(toIndentedString(startUserId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

