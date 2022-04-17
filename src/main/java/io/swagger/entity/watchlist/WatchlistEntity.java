package io.swagger.entity.watchlist;

import io.swagger.entity.media.MediaCache;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Boolean isPubliclyViewable;
    private UUID ownerUserId;

    // Since TitleData is stored as blob data, saving and retrieving is quite crazy
//    // Media Ids are stored in "query" column
//    @ManyToMany
//    @JoinColumn(name = "query", referencedColumnName = "id")
//    private List<MediaCache> mediaItems;
}
