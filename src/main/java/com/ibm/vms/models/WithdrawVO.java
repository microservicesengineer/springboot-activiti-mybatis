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
 * WithdrawVO
 */
@Validated

public class WithdrawVO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("processid")
  private String processid = null;

  public WithdrawVO processid(String processid) {
    this.processid = processid;
    return this;
  }

  /**
   * Get processid
   * @return processid
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getProcessid() {
    return processid;
  }

  public void setProcessid(String processid) {
    this.processid = processid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WithdrawVO withdrawVO = (WithdrawVO) o;
    return Objects.equals(this.processid, withdrawVO.processid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WithdrawVO {\n");
    
    sb.append("    processid: ").append(toIndentedString(processid)).append("\n");
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

