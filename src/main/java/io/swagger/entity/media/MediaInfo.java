package io.swagger.entity.media;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaInfo {
    @Id
    private String id;
    private String title;
    private Integer year;

    @Override
    public String toString() {
        return "MediaInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
