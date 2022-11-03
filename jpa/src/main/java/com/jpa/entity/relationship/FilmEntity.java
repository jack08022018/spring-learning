package com.jpa.entity.relationship;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "film")
public class FilmEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "language_id")
    private Integer languageId;

    @ManyToMany(
            mappedBy = "films",
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    private List<ActorEntity> actors = new ArrayList<>();

    public void addActors(List<ActorEntity> data) {
        actors.addAll(data);
    }

}
