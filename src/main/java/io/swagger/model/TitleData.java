package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.ActorShort;
import io.swagger.model.KeyValueItem;
import io.swagger.model.UserRating;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TitleData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:29:35.803425-04:00[America/New_York]")


public class TitleData   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("ratings")
  @Valid
  private List<UserRating> ratings = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("originalTitle")
  private String originalTitle = null;

  @JsonProperty("fullTitle")
  private String fullTitle = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("year")
  private String year = null;

  @JsonProperty("image")
  private String image = null;

  @JsonProperty("releaseDate")
  private String releaseDate = null;

  @JsonProperty("runtimeMins")
  private String runtimeMins = null;

  @JsonProperty("runtimeStr")
  private String runtimeStr = null;

  @JsonProperty("plot")
  private String plot = null;

  @JsonProperty("actorList")
  @Valid
  private List<ActorShort> actorList = null;

  @JsonProperty("genres")
  private String genres = null;

  @JsonProperty("genreList")
  @Valid
  private List<KeyValueItem> genreList = null;

  public TitleData id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "tt0411008", description = "")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TitleData ratings(List<UserRating> ratings) {
    this.ratings = ratings;
    return this;
  }

  public TitleData addRatingsItem(UserRating ratingsItem) {
    if (this.ratings == null) {
      this.ratings = new ArrayList<UserRating>();
    }
    this.ratings.add(ratingsItem);
    return this;
  }

  /**
   * Get ratings
   * @return ratings
   **/
  @Schema(description = "")
      @Valid
    public List<UserRating> getRatings() {
    return ratings;
  }

  public void setRatings(List<UserRating> ratings) {
    this.ratings = ratings;
  }

  public TitleData title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @Schema(example = "Lost", description = "")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TitleData originalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
    return this;
  }

  /**
   * Get originalTitle
   * @return originalTitle
   **/
  @Schema(description = "")
  
    public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public TitleData fullTitle(String fullTitle) {
    this.fullTitle = fullTitle;
    return this;
  }

  /**
   * Get fullTitle
   * @return fullTitle
   **/
  @Schema(example = "Lost (TV Series 2004–2010)", description = "")
  
    public String getFullTitle() {
    return fullTitle;
  }

  public void setFullTitle(String fullTitle) {
    this.fullTitle = fullTitle;
  }

  public TitleData type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @Schema(example = "TVSeries", description = "")
  
    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TitleData year(String year) {
    this.year = year;
    return this;
  }

  /**
   * Get year
   * @return year
   **/
  @Schema(example = "2004", description = "")
  
    public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public TitleData image(String image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
   **/
  @Schema(example = "https://imdb-api.com/images/original/MV5BNzhlY2E5NDUtYjJjYy00ODg3LWFkZWQtYTVmMzU4ZWZmOWJkXkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.6751_AL_.jpg", description = "")
  
    public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public TitleData releaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
    return this;
  }

  /**
   * Get releaseDate
   * @return releaseDate
   **/
  @Schema(example = "2004-09-22", description = "")
  
    public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public TitleData runtimeMins(String runtimeMins) {
    this.runtimeMins = runtimeMins;
    return this;
  }

  /**
   * Get runtimeMins
   * @return runtimeMins
   **/
  @Schema(description = "")
  
    public String getRuntimeMins() {
    return runtimeMins;
  }

  public void setRuntimeMins(String runtimeMins) {
    this.runtimeMins = runtimeMins;
  }

  public TitleData runtimeStr(String runtimeStr) {
    this.runtimeStr = runtimeStr;
    return this;
  }

  /**
   * Get runtimeStr
   * @return runtimeStr
   **/
  @Schema(description = "")
  
    public String getRuntimeStr() {
    return runtimeStr;
  }

  public void setRuntimeStr(String runtimeStr) {
    this.runtimeStr = runtimeStr;
  }

  public TitleData plot(String plot) {
    this.plot = plot;
    return this;
  }

  /**
   * Get plot
   * @return plot
   **/
  @Schema(example = "The past, present, and future lives of surviving Oceanic Flight 815 passengers are dramatically intertwined as a fight for survival ensues in a quest for answers after crashlanding on a mysterious island. Each discovery prompts yet more secrets, as the hastily-formed colony search for a way off the island, or is this their home?", description = "")
  
    public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }

  public TitleData actorList(List<ActorShort> actorList) {
    this.actorList = actorList;
    return this;
  }

  public TitleData addActorListItem(ActorShort actorListItem) {
    if (this.actorList == null) {
      this.actorList = new ArrayList<ActorShort>();
    }
    this.actorList.add(actorListItem);
    return this;
  }

  /**
   * Get actorList
   * @return actorList
   **/
  @Schema(description = "")
      @Valid
    public List<ActorShort> getActorList() {
    return actorList;
  }

  public void setActorList(List<ActorShort> actorList) {
    this.actorList = actorList;
  }

  public TitleData genres(String genres) {
    this.genres = genres;
    return this;
  }

  /**
   * Get genres
   * @return genres
   **/
  @Schema(example = "Adventure, Drama, Fantasy", description = "")
  
    public String getGenres() {
    return genres;
  }

  public void setGenres(String genres) {
    this.genres = genres;
  }

  public TitleData genreList(List<KeyValueItem> genreList) {
    this.genreList = genreList;
    return this;
  }

  public TitleData addGenreListItem(KeyValueItem genreListItem) {
    if (this.genreList == null) {
      this.genreList = new ArrayList<KeyValueItem>();
    }
    this.genreList.add(genreListItem);
    return this;
  }

  /**
   * Get genreList
   * @return genreList
   **/
  @Schema(description = "")
      @Valid
    public List<KeyValueItem> getGenreList() {
    return genreList;
  }

  public void setGenreList(List<KeyValueItem> genreList) {
    this.genreList = genreList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TitleData titleData = (TitleData) o;
    return Objects.equals(this.id, titleData.id) &&
        Objects.equals(this.ratings, titleData.ratings) &&
        Objects.equals(this.title, titleData.title) &&
        Objects.equals(this.originalTitle, titleData.originalTitle) &&
        Objects.equals(this.fullTitle, titleData.fullTitle) &&
        Objects.equals(this.type, titleData.type) &&
        Objects.equals(this.year, titleData.year) &&
        Objects.equals(this.image, titleData.image) &&
        Objects.equals(this.releaseDate, titleData.releaseDate) &&
        Objects.equals(this.runtimeMins, titleData.runtimeMins) &&
        Objects.equals(this.runtimeStr, titleData.runtimeStr) &&
        Objects.equals(this.plot, titleData.plot) &&
        Objects.equals(this.actorList, titleData.actorList) &&
        Objects.equals(this.genres, titleData.genres) &&
        Objects.equals(this.genreList, titleData.genreList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ratings, title, originalTitle, fullTitle, type, year, image, releaseDate, runtimeMins, runtimeStr, plot, actorList, genres, genreList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TitleData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ratings: ").append(toIndentedString(ratings)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    originalTitle: ").append(toIndentedString(originalTitle)).append("\n");
    sb.append("    fullTitle: ").append(toIndentedString(fullTitle)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    year: ").append(toIndentedString(year)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    releaseDate: ").append(toIndentedString(releaseDate)).append("\n");
    sb.append("    runtimeMins: ").append(toIndentedString(runtimeMins)).append("\n");
    sb.append("    runtimeStr: ").append(toIndentedString(runtimeStr)).append("\n");
    sb.append("    plot: ").append(toIndentedString(plot)).append("\n");
    sb.append("    actorList: ").append(toIndentedString(actorList)).append("\n");
    sb.append("    genres: ").append(toIndentedString(genres)).append("\n");
    sb.append("    genreList: ").append(toIndentedString(genreList)).append("\n");
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
