package com.ibm.vms.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HistroicTaskInstance
 */
@Validated

public class HistroicTaskInstance  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("deleteReason")
  private String deleteReason = null;

  @JsonProperty("startTime")
  private OffsetDateTime startTime = null;

  @JsonProperty("endTime")
  private OffsetDateTime endTime = null;

  @JsonProperty("durationInMillis")
  private Long durationInMillis = null;

  @JsonProperty("workTimeInMillis")
  private Long workTimeInMillis = null;

  @JsonProperty("claimTime")
  private OffsetDateTime claimTime = null;

  public HistroicTaskInstance deleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
    return this;
  }

  /**
   * Get deleteReason
   * @return deleteReason
  **/
  @ApiModelProperty(value = "")


  public String getDeleteReason() {
    return deleteReason;
  }

  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }

  public HistroicTaskInstance startTime(OffsetDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public HistroicTaskInstance endTime(OffsetDateTime endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * Get endTime
   * @return endTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(OffsetDateTime endTime) {
    this.endTime = endTime;
  }

  public HistroicTaskInstance durationInMillis(Long durationInMillis) {
    this.durationInMillis = durationInMillis;
    return this;
  }

  /**
   * Get durationInMillis
   * @return durationInMillis
  **/
  @ApiModelProperty(value = "")


  public Long getDurationInMillis() {
    return durationInMillis;
  }

  public void setDurationInMillis(Long durationInMillis) {
    this.durationInMillis = durationInMillis;
  }

  public HistroicTaskInstance workTimeInMillis(Long workTimeInMillis) {
    this.workTimeInMillis = workTimeInMillis;
    return this;
  }

  /**
   * Get workTimeInMillis
   * @return workTimeInMillis
  **/
  @ApiModelProperty(value = "")


  public Long getWorkTimeInMillis() {
    return workTimeInMillis;
  }

  public void setWorkTimeInMillis(Long workTimeInMillis) {
    this.workTimeInMillis = workTimeInMillis;
  }

  public HistroicTaskInstance claimTime(OffsetDateTime claimTime) {
    this.claimTime = claimTime;
    return this;
  }

  /**
   * Get claimTime
   * @return claimTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getClaimTime() {
    return claimTime;
  }

  public void setClaimTime(OffsetDateTime claimTime) {
    this.claimTime = claimTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistroicTaskInstance histroicTaskInstance = (HistroicTaskInstance) o;
    return Objects.equals(this.deleteReason, histroicTaskInstance.deleteReason) &&
        Objects.equals(this.startTime, histroicTaskInstance.startTime) &&
        Objects.equals(this.endTime, histroicTaskInstance.endTime) &&
        Objects.equals(this.durationInMillis, histroicTaskInstance.durationInMillis) &&
        Objects.equals(this.workTimeInMillis, histroicTaskInstance.workTimeInMillis) &&
        Objects.equals(this.claimTime, histroicTaskInstance.claimTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deleteReason, startTime, endTime, durationInMillis, workTimeInMillis, claimTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistroicTaskInstance {\n");
    
    sb.append("    deleteReason: ").append(toIndentedString(deleteReason)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    durationInMillis: ").append(toIndentedString(durationInMillis)).append("\n");
    sb.append("    workTimeInMillis: ").append(toIndentedString(workTimeInMillis)).append("\n");
    sb.append("    claimTime: ").append(toIndentedString(claimTime)).append("\n");
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

