package com.graphql.repository;

import com.graphql.entity.CityEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
    @EntityGraph(attributePaths = {"country"})
    List<CityEntity> findByCity(String city);
}
