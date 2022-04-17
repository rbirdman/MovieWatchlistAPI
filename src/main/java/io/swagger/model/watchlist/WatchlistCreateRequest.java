package io.swagger.model.watchlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.media.MediaItem;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * WatchlistCreateRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-04-02T22:43:09.213512-04:00[America/New_York]")


public class WatchlistCreateRequest {
  @JsonProperty("isPubliclyViewable")
  private Boolean isPubliclyViewable = null;

  @JsonProperty("ownerUserId")
  private UUID ownerUserId = null;

  @JsonProperty("mediaItems")
  @Valid
  private List<MediaItem> mediaItems = new ArrayList<MediaItem>();

  public WatchlistCreateRequest isPubliclyViewable(Boolean isPubliclyViewable) {
    this.isPubliclyViewable = isPubliclyViewable;
    return this;
  }

  /**
   * Get isPubliclyViewable
   * @return isPubliclyViewable
   **/
  @Schema(example = "true", required = true, description = "")
      @NotNull

    public Boolean isIsPubliclyViewable() {
    return isPubliclyViewable;
  }

  public void setIsPubliclyViewable(Boolean isPubliclyViewable) {
    this.isPubliclyViewable = isPubliclyViewable;
  }

  public WatchlistCreateRequest ownerUserId(UUID ownerUserId) {
    this.ownerUserId = ownerUserId;
    return this;
  }

  /**
   * Get ownerUserId
   * @return ownerUserId
   **/
  @Schema(example = "3", required = true, description = "")
      @NotNull

    public UUID getOwnerUserId() {
    return ownerUserId;
  }

  public void setOwnerUserId(UUID ownerUserId) {
    this.ownerUserId = ownerUserId;
  }

  public WatchlistCreateRequest mediaItems(List<MediaItem> mediaItems) {
    this.mediaItems = mediaItems;
    return this;
  }

  public WatchlistCreateRequest addMediaItemsItem(MediaItem mediaItemsItem) {
    this.mediaItems.add(mediaItemsItem);
    return this;
  }

  /**
   * Get mediaItems
   * @return mediaItems
   **/
  @Schema(required = true, description = "")
      @NotNull
    @Valid
    public List<MediaItem> getMediaItems() {
    return mediaItems;
  }

  public void setMediaItems(List<MediaItem> mediaItems) {
    this.mediaItems = mediaItems;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WatchlistCreateRequest watchlist = (WatchlistCreateRequest) o;
    return Objects.equals(this.isPubliclyViewable, watchlist.isPubliclyViewable) &&
        Objects.equals(this.ownerUserId, watchlist.ownerUserId) &&
        Objects.equals(this.mediaItems, watchlist.mediaItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isPubliclyViewable, ownerUserId, mediaItems);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WatchlistCreateRequest {\n");
    
    sb.append("    isPubliclyViewable: ").append(toIndentedString(isPubliclyViewable)).append("\n");
    sb.append("    ownerUserId: ").append(toIndentedString(ownerUserId)).append("\n");
    sb.append("    mediaItems: ").append(toIndentedString(mediaItems)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
