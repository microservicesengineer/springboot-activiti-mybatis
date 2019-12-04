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
 * CandidateUserVO
 */
@Validated

public class CandidateUserVO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("candidateUser")
  private String candidateUser = null;

  public CandidateUserVO candidateUser(String candidateUser) {
    this.candidateUser = candidateUser;
    return this;
  }

  /**
   * Get candidateUser
   * @return candidateUser
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCandidateUser() {
    return candidateUser;
  }

  public void setCandidateUser(String candidateUser) {
    this.candidateUser = candidateUser;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CandidateUserVO candidateUserVO = (CandidateUserVO) o;
    return Objects.equals(this.candidateUser, candidateUserVO.candidateUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(candidateUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CandidateUserVO {\n");
    
    sb.append("    candidateUser: ").append(toIndentedString(candidateUser)).append("\n");
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

