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
 * QueryTaskReqVO
 */
@Validated

public class QueryTaskReqVO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("firstResult")
  private Integer firstResult = null;

  @JsonProperty("maxResults")
  private Integer maxResults = null;

  @JsonProperty("assignee")
  private String assignee = null;

  @JsonProperty("candidateUser")
  private String candidateUser = null;

  @JsonProperty("candidateGroup")
  private String candidateGroup = null;

  @JsonProperty("owner")
  private String owner = null;

  public QueryTaskReqVO firstResult(Integer firstResult) {
    this.firstResult = firstResult;
    return this;
  }

  /**
   * Get firstResult
   * @return firstResult
  **/
  @ApiModelProperty(value = "")


  public Integer getFirstResult() {
    return firstResult;
  }

  public void setFirstResult(Integer firstResult) {
    this.firstResult = firstResult;
  }

  public QueryTaskReqVO maxResults(Integer maxResults) {
    this.maxResults = maxResults;
    return this;
  }

  /**
   * Get maxResults
   * @return maxResults
  **/
  @ApiModelProperty(value = "")


  public Integer getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }

  public QueryTaskReqVO assignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

  /**
   * Get assignee
   * @return assignee
  **/
  @ApiModelProperty(value = "")


  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public QueryTaskReqVO candidateUser(String candidateUser) {
    this.candidateUser = candidateUser;
    return this;
  }

  /**
   * Get candidateUser
   * @return candidateUser
  **/
  @ApiModelProperty(value = "")


  public String getCandidateUser() {
    return candidateUser;
  }

  public void setCandidateUser(String candidateUser) {
    this.candidateUser = candidateUser;
  }

  public QueryTaskReqVO candidateGroup(String candidateGroup) {
    this.candidateGroup = candidateGroup;
    return this;
  }

  /**
   * Get candidateGroup
   * @return candidateGroup
  **/
  @ApiModelProperty(value = "")


  public String getCandidateGroup() {
    return candidateGroup;
  }

  public void setCandidateGroup(String candidateGroup) {
    this.candidateGroup = candidateGroup;
  }

  public QueryTaskReqVO owner(String owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  **/
  @ApiModelProperty(value = "")


  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryTaskReqVO queryTaskReqVO = (QueryTaskReqVO) o;
    return Objects.equals(this.firstResult, queryTaskReqVO.firstResult) &&
        Objects.equals(this.maxResults, queryTaskReqVO.maxResults) &&
        Objects.equals(this.assignee, queryTaskReqVO.assignee) &&
        Objects.equals(this.candidateUser, queryTaskReqVO.candidateUser) &&
        Objects.equals(this.candidateGroup, queryTaskReqVO.candidateGroup) &&
        Objects.equals(this.owner, queryTaskReqVO.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstResult, maxResults, assignee, candidateUser, candidateGroup, owner);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryTaskReqVO {\n");
    
    sb.append("    firstResult: ").append(toIndentedString(firstResult)).append("\n");
    sb.append("    maxResults: ").append(toIndentedString(maxResults)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
    sb.append("    candidateUser: ").append(toIndentedString(candidateUser)).append("\n");
    sb.append("    candidateGroup: ").append(toIndentedString(candidateGroup)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
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

