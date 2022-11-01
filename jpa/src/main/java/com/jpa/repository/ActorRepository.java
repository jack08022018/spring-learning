package com.jpa.repository;

import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
}
