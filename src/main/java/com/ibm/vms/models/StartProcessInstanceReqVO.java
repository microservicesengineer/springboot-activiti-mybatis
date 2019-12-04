package com.ibm.vms.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * StartProcessInstanceReqVO
 */
@Validated

public class StartProcessInstanceReqVO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("instanceKey")
  private String instanceKey = null;

  @JsonProperty("applyUserId")
  private String applyUserId = null;

  @JsonProperty("businessKey")
  private String businessKey = null;

  @JsonProperty("variables")
  @Valid
  private Map<String, Object> variables = null;

  public StartProcessInstanceReqVO instanceKey(String instanceKey) {
    this.instanceKey = instanceKey;
    return this;
  }

  /**
   * Get instanceKey
   * @return instanceKey
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getInstanceKey() {
    return instanceKey;
  }

  public void setInstanceKey(String instanceKey) {
    this.instanceKey = instanceKey;
  }

  public StartProcessInstanceReqVO applyUserId(String applyUserId) {
    this.applyUserId = applyUserId;
    return this;
  }

  /**
   * Get applyUserId
   * @return applyUserId
  **/
  @ApiModelProperty(value = "")


  public String getApplyUserId() {
    return applyUserId;
  }

  public void setApplyUserId(String applyUserId) {
    this.applyUserId = applyUserId;
  }

  public StartProcessInstanceReqVO businessKey(String businessKey) {
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

  public StartProcessInstanceReqVO variables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public StartProcessInstanceReqVO putVariablesItem(String key, Object variablesItem) {
    if (this.variables == null) {
      this.variables = new HashMap<>();
    }
    this.variables.put(key, variablesItem);
    return this;
  }

  /**
   * Get variables
   * @return variables
  **/
  @ApiModelProperty(value = "")


  public Map<String, Object> getVariables() {
    return variables;
  }

  public void setVariables(Map<String, Object> variables) {
    this.variables = variables;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartProcessInstanceReqVO startProcessInstanceReqVO = (StartProcessInstanceReqVO) o;
    return Objects.equals(this.instanceKey, startProcessInstanceReqVO.instanceKey) &&
        Objects.equals(this.applyUserId, startProcessInstanceReqVO.applyUserId) &&
        Objects.equals(this.businessKey, startProcessInstanceReqVO.businessKey) &&
        Objects.equals(this.variables, startProcessInstanceReqVO.variables);
  }

  @Override
  public int hashCode() {
    return Objects.hash(instanceKey, applyUserId, businessKey, variables);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartProcessInstanceReqVO {\n");
    
    sb.append("    instanceKey: ").append(toIndentedString(instanceKey)).append("\n");
    sb.append("    applyUserId: ").append(toIndentedString(applyUserId)).append("\n");
    sb.append("    businessKey: ").append(toIndentedString(businessKey)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
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

