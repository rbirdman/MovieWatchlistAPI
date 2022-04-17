package io.swagger.entity.watchlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistMedia {
    // JPA requires an ID. Make it an integer for now for simplicity.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private UUID WatchlistId;
    private String MediaId;
}
