package com.ukg.repository;

import com.ukg.model.ApplicationsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationsModel, Long> {
    List<ApplicationsModel> findByEmployee_empID(Long empID);
    List<ApplicationsModel> findByOpening_OpeningId(Long openingId);
}
