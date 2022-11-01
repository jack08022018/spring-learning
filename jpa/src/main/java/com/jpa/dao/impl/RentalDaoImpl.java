package com.jpa.dao.impl;

import com.jpa.dao.RentalDao;
import com.jpa.dto.MovieRentalDto;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalDaoImpl implements RentalDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovieRentalDto> getRentalMovies(String title) {
        List<Tuple> tupleList = entityManager.createNativeQuery("""
                    SELECT C.title, A.rental_date
                    FROM rental A
                        INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                        INNER JOIN film C ON C.film_id = B.film_id
                    WHERE C.title LIKE '%'||:title||'%'
                    ORDER BY C.title
                """, Tuple.class)
                .setParameter("title", title)
                .unwrap(org.hibernate.query.Query.class)
                .getResultList();
        return tupleList.stream()
                .map(s -> {
                    Timestamp rental_date = (Timestamp) s.get("rental_date");
                    return new MovieRentalDto().builder()
                                .title((String) s.get("title"))
                                .rentalDate(rental_date.toLocalDateTime())
                                .build();
                })
                .collect(Collectors.toList());
    }
}
