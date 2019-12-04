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
 * CompleteTaskReqVO
 */
@Validated

public class CompleteTaskReqVO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("taskId")
  private String taskId = null;

  @JsonProperty("assignee")
  private String assignee = null;

  @JsonProperty("isReviewPass")
  private Integer isReviewPass = null;

  @JsonProperty("returnStart")
  private Integer returnStart = null;

  @JsonProperty("variables")
  @Valid
  private Map<String, Object> variables = null;

  @JsonProperty("comment")
  private String comment = null;

  public CompleteTaskReqVO taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  /**
   * Get taskId
   * @return taskId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public CompleteTaskReqVO assignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

  /**
   * Get assignee
   * @return assignee
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public CompleteTaskReqVO isReviewPass(Integer isReviewPass) {
    this.isReviewPass = isReviewPass;
    return this;
  }

  /**
   * Get isReviewPass
   * @return isReviewPass
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getIsReviewPass() {
    return isReviewPass;
  }

  public void setIsReviewPass(Integer isReviewPass) {
    this.isReviewPass = isReviewPass;
  }

  public CompleteTaskReqVO returnStart(Integer returnStart) {
    this.returnStart = returnStart;
    return this;
  }

  /**
   * Get returnStart
   * @return returnStart
  **/
  @ApiModelProperty(value = "")


  public Integer getReturnStart() {
    return returnStart;
  }

  public void setReturnStart(Integer returnStart) {
    this.returnStart = returnStart;
  }

  public CompleteTaskReqVO variables(Map<String, Object> variables) {
    this.variables = variables;
    return this;
  }

  public CompleteTaskReqVO putVariablesItem(String key, Object variablesItem) {
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

  public CompleteTaskReqVO comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
  **/
  @ApiModelProperty(value = "")


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompleteTaskReqVO completeTaskReqVO = (CompleteTaskReqVO) o;
    return Objects.equals(this.taskId, completeTaskReqVO.taskId) &&
        Objects.equals(this.assignee, completeTaskReqVO.assignee) &&
        Objects.equals(this.isReviewPass, completeTaskReqVO.isReviewPass) &&
        Objects.equals(this.returnStart, completeTaskReqVO.returnStart) &&
        Objects.equals(this.variables, completeTaskReqVO.variables) &&
        Objects.equals(this.comment, completeTaskReqVO.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taskId, assignee, isReviewPass, returnStart, variables, comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompleteTaskReqVO {\n");
    
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
    sb.append("    isReviewPass: ").append(toIndentedString(isReviewPass)).append("\n");
    sb.append("    returnStart: ").append(toIndentedString(returnStart)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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

