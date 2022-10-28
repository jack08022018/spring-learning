package com.jpa.repository;

import com.jpa.repository.dto.MovieRentalInterface;
import com.jpa.repository.dto.MovieRentalRecord;
import com.jpa.repository.entity.RentalEntity;
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
}
