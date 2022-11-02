package com.jpa.repository;

import com.jpa.entity.SalariesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalariesRepository extends JpaRepository<SalariesEntity, Integer> {
    List<SalariesEntity> findByEmpNo(Integer empNo);
}
