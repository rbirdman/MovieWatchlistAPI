package io.swagger.model.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SearchData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")


public class SearchData extends RepresentationModel<SearchData> {
  @JsonProperty("searchType")
  private String searchType = null;

  @JsonProperty("expression")
  private String expression = null;

  @JsonProperty("results")
  @Valid
  private List<SearchResult> results = null;

  @JsonProperty("errorMessage")
  private String errorMessage = null;

  public SearchData searchType(String searchType) {
    this.searchType = searchType;
    return this;
  }

  /**
   * Get searchType
   * @return searchType
   **/
  @Schema(example = "Title", required = true, description = "")
      @NotNull

    public String getSearchType() {
    return searchType;
  }

  public void setSearchType(String searchType) {
    this.searchType = searchType;
  }

  public SearchData expression(String expression) {
    this.expression = expression;
    return this;
  }

  /**
   * Get expression
   * @return expression
   **/
  @Schema(example = "Frozen", required = true, description = "")
      @NotNull

    public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public SearchData results(List<SearchResult> results) {
    this.results = results;
    return this;
  }

  public SearchData addResultsItem(SearchResult resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<SearchResult>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
   **/
  @Schema(description = "")
      @Valid
    public List<SearchResult> getResults() {
    return results;
  }

  public void setResults(List<SearchResult> results) {
    this.results = results;
  }

  public SearchData errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Get errorMessage
   * @return errorMessage
   **/
  @Schema(description = "")
  
    public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchData searchData = (SearchData) o;
    return Objects.equals(this.searchType, searchData.searchType) &&
        Objects.equals(this.expression, searchData.expression) &&
        Objects.equals(this.results, searchData.results) &&
        Objects.equals(this.errorMessage, searchData.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(searchType, expression, results, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchData {\n");
    
    sb.append("    searchType: ").append(toIndentedString(searchType)).append("\n");
    sb.append("    expression: ").append(toIndentedString(expression)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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
