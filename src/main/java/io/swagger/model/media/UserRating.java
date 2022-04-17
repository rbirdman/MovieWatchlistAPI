package io.swagger.model.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * UserRating
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")


public class UserRating   {
  @JsonProperty("rating")
  private Integer rating = null;

  @JsonProperty("email")
  private String email = null;

  public UserRating(Integer rating, String email) {
    this.rating = rating;
    this.email = email;
  }

  /**
   * Get rating
   * minimum: 1
   * maximum: 5
   * @return rating
   **/
  @Schema(example = "4", description = "")
  
  @Min(1) @Max(5)   public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  /**
   * User email
   * @return user email
   **/
  @Schema(example = "fli34@jh.edu", description = "")

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRating userRating = (UserRating) o;
    return Objects.equals(this.rating, userRating.rating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rating);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRating {\n");
    
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
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
