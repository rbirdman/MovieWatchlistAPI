package io.swagger.model.watchlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class WatchlistVisiblity {
    @JsonProperty("isPubliclyViewable")
    private Boolean isPubliclyViewable = null;

    public WatchlistVisiblity isPubliclyViewable(Boolean isPubliclyViewable) {
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

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WatchlistVisiblity watchlistVisiblity = (WatchlistVisiblity) o;
        return Objects.equals(this.isPubliclyViewable, watchlistVisiblity.isPubliclyViewable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPubliclyViewable);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WatchlistVisiblity {\n");

        sb.append("    isPubliclyViewable: ").append(toIndentedString(isPubliclyViewable)).append("\n");
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
