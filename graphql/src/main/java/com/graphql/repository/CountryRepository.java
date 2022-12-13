package com.graphql.repository;

import com.graphql.entity.CityEntity;
import com.graphql.entity.CountryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
    boolean existsById(Integer id);

    @EntityGraph(attributePaths = {"cities"})
    List<CountryEntity> findByCountry(String country);
}
