package io.swagger.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * TokenCredentials
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")


public class TokenCredentials extends RepresentationModel<TokenCredentials> {
  @JsonProperty("accessToken")
  private String accessToken = null;

  @JsonProperty("userId")
  private UUID userId = null;

  public TokenCredentials accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  public TokenCredentials userId(UUID userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get accessToken
   * @return accessToken
   **/
  @Schema(example = "Bearer ey122asd...", required = true, description = "")
      @NotNull

    public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  /**
   * Get userId
   * @return userId
   **/
  @Schema(example = "71bc52d4-c3df-11ec-9d64-0242ac120002", required = true, description = "")
  @NotNull

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenCredentials tokenCredentials = (TokenCredentials) o;
    return Objects.equals(this.accessToken, tokenCredentials.accessToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenCredentials {\n");
    
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
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
