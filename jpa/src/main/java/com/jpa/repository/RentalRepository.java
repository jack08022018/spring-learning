package com.jpa.repository;

import com.jpa.dto.MovieRentalDto;
import com.jpa.dto.MovieRentalInterface;
import com.jpa.dto.MovieRentalRecord;
import com.jpa.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {
    @Query(nativeQuery = true, value = """
            SELECT C.title, A.rental_date
            FROM rental A
                INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                INNER JOIN film C ON C.film_id = B.film_id
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<MovieRentalInterface> getRentalMoviesProjection(@Param("title") String title);

    @Query(nativeQuery = true, value = """
            SELECT C.title, A.rental_date
            FROM rental A
                INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                INNER JOIN film C ON C.film_id = B.film_id
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<Tuple> getRentalMoviesTuple(@Param("title") String title);

    @Query(nativeQuery = true, value = """
            SELECT C.title, A.rental_date
            FROM rental A
                INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                INNER JOIN film C ON C.film_id = B.film_id
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<MovieRentalRecord> getRentalMoviesRecord(@Param("title") String title);

    @Query(value = """
            select new com.jpa.dto.MovieRentalDto(
                C.title, A.rentalDate
            )
            FROM RentalEntity A
                INNER JOIN InventoryEntity B ON B.inventoryId = A.inventoryId
                INNER JOIN FilmEntity C ON C.filmId = B.filmId
            WHERE C.title LIKE %:title%
            ORDER BY C.title
        """)
    List<MovieRentalDto> getRentalMoviesDto(@Param("title") String title);
}
