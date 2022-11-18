package com.jpa.dao.impl;

import com.jpa.dao.RentalDao;
import com.jpa.dto.CityDto;
import com.jpa.dto.MovieRentalDto;
import com.jpa.dto.PropertyDto;
import com.jpa.dto.StaffRentalDto;
import com.jpa.entity.relationship.ActorEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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
                .setHint("org.hibernate.readOnly", true)
                .unwrap(Query.class)
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
                .unwrap(Query.class)
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

    @Override
    public List<PropertyDto> mapRowToColumn() {
        String query = """
                    SELECT
                        p.service_name AS "serviceName",
                        p.component_name AS "componentName",
                        MAX(
                            CASE WHEN property_name = 'databaseName'
                            THEN property_value END
                        ) AS "databaseName",
                        MAX(
                            CASE WHEN property_name = 'url'
                            THEN property_value END)
                        AS "url",
                        MAX(
                            CASE WHEN property_name = 'serverName'
                            THEN property_value END
                        ) AS "serverName",
                        MAX(
                            CASE WHEN property_name = 'username'
                            THEN property_value END
                        ) AS "userName",
                        MAX(
                            CASE WHEN property_name = 'password'
                            THEN property_value END
                        ) AS "password"
                    FROM Property p
                    WHERE p.component_name = 'dataSource'
                    GROUP BY p.service_name, p.component_name
                """;
        List<Tuple> tupleList = entityManager.createNativeQuery(query, Tuple.class)
                .unwrap(Query.class)
                .getResultList();
        return tupleList.stream()
                .map(s -> new PropertyDto().builder()
                        .componentName((String) s.get("componentName"))
                        .databaseName((String) s.get("databaseName"))
                        .password((String) s.get("password"))
                        .serverName((String) s.get("serverName"))
                        .serviceName((String) s.get("serviceName"))
                        .url((String) s.get("url"))
                        .userName((String) s.get("userName"))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDto> getPartition() {
        String query = """
                    WITH DATA_PARTITION AS (
                       SELECT *, ROW_NUMBER()
                       over (
                          PARTITION BY country_id
                          order by city_id desc
                       ) AS ROW_NO
                       FROM city
                       WHERE city_id < 30
                    )
                    SELECT city_id, city, country_id
                    FROM DATA_PARTITION
                    WHERE ROW_NO = 1
                    ORDER BY country_id desc
                """;
        List<Tuple> tupleList = entityManager.createNativeQuery(query, Tuple.class)
                .unwrap(Query.class)
                .getResultList();
        return tupleList.stream()
                .map(s -> new CityDto().builder()
                        .city((String) s.get("city"))
                        .country_id((Integer) s.get("country_id"))
                        .city_id((Integer) s.get("city_id"))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ActorEntity findActorWithLock(int id) {
        ActorEntity entity = entityManager.find(ActorEntity.class, id, LockModeType.OPTIMISTIC);
        return entity;
    }
}
