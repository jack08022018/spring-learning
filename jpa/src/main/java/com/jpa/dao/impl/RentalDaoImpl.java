package com.jpa.dao.impl;

import com.jpa.dao.RentalDao;
import com.jpa.dto.CityDto;
import com.jpa.dto.MovieRentalDto;
import com.jpa.dto.StaffRentalDto;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
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
        entityManager.unwrap(Session.class).setJdbcBatchSize(10);
        String query = """
                    SELECT C.title, A.rental_date
                    FROM rental A
                        INNER JOIN inventory B ON B.inventory_id = A.inventory_id
                        INNER JOIN film C ON C.film_id = B.film_id
                    WHERE C.title LIKE '%'||:title||'%'
                    ORDER BY C.title
                """;
        List<Tuple> tupleList = entityManager.createNativeQuery(query, Tuple.class)
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

    @Override
    public List<CityDto> getCommonTableExpression() {
        String query = """
                    WITH
                    COUNTRY_DATA AS (
                    	SELECT *
                    	FROM country
                    	WHERE country_id < 30
                    ),
                    CITY_DATA AS (
                    	SELECT *
                    	FROM city
                    	WHERE city_id < 100
                    )
                    SELECT A.country, b.city, b.last_update
                    FROM COUNTRY_DATA A
                    	INNER JOIN CITY_DATA B
                    		ON B.country_id = A.country_id
                """;
        List<Tuple> tupleList = entityManager.createNativeQuery(query, Tuple.class)
                .unwrap(org.hibernate.query.Query.class)
                .getResultList();
        return tupleList.stream()
                .map(s -> {
                    Timestamp last_update = (Timestamp) s.get("last_update");
                    return new CityDto().builder()
                            .country((String) s.get("country"))
                            .city((String) s.get("city"))
                            .lastUpdate(last_update.toLocalDateTime())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
