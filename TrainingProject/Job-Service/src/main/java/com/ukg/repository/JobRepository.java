package com.ukg.repository;

import com.ukg.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobRepository extends JpaRepository<JobModel,Long> {
}
