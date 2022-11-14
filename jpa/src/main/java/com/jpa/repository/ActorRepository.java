package com.jpa.repository;

import com.jpa.dto.MovieRentalInterface;
import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(value = """
            SELECT a
            FROM ActorEntity a
            WHERE a.actorId = :id
        """)
    ActorEntity findLock(@Param("id") int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<ActorEntity> findById(Integer actorId);
}
