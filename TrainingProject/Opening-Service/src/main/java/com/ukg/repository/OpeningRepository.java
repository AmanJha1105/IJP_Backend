package com.ukg.repository;


import com.ukg.model.OpeningModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningRepository extends JpaRepository<OpeningModel, Long> {
}
