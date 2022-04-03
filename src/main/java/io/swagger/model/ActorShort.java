package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ActorShort
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")


public class ActorShort   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("image")
  private String image = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("asCharacter")
  private String asCharacter = null;

  public ActorShort id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "nm0306201", description = "")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ActorShort image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
   **/
  @Schema(example = "https://imdb-api.com/images/original/MV5BMTUyNTkxODIxN15BMl5BanBnXkFtZTgwOTU2MDAwMTE@._V1_Ratio1.0000_AL_.jpg", description = "")
  
    public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public ActorShort name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(example = "Jorge Garcia", description = "")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ActorShort asCharacter(String asCharacter) {
    this.asCharacter = asCharacter;
    return this;
  }

  /**
   * Get asCharacter
   * @return asCharacter
   **/
  @Schema(example = "Hugo 'Hurley", description = "")
  
    public String getAsCharacter() {
    return asCharacter;
  }

  public void setAsCharacter(String asCharacter) {
    this.asCharacter = asCharacter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActorShort actorShort = (ActorShort) o;
    return Objects.equals(this.id, actorShort.id) &&
        Objects.equals(this.image, actorShort.image) &&
        Objects.equals(this.name, actorShort.name) &&
        Objects.equals(this.asCharacter, actorShort.asCharacter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, image, name, asCharacter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActorShort {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    asCharacter: ").append(toIndentedString(asCharacter)).append("\n");
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
