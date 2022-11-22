package com.jpa.repository;

import com.jpa.dto.MovieRentalInterface;
import com.jpa.entity.relationship.ActorEntity;
import com.jpa.entity.relationship.CityEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    @Lock(LockModeType.OPTIMISTIC)
    @Query(value = """
            SELECT a
            FROM ActorEntity a
            WHERE a.actorId = :id
        """)
    ActorEntity findLockOptimistic(@Param("id") int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = """
            SELECT a
            FROM ActorEntity a
            WHERE a.actorId = :id
        """)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    ActorEntity findLockPessimistic(@Param("id") int id);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
//    Optional<ActorEntity> findById(Integer actorId);

    @EntityGraph(attributePaths = {"films"})
    Optional<ActorEntity> findById(Integer actorId);

}
