package com.ukg.FeignClient;


import com.ukg.dto.JobDTO;
import com.ukg.model.JobModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "job-service")
public interface JobClient {

    @GetMapping("/api/jobs/{jobId}")
    JobModel getJobById(@PathVariable("jobId") Long jobId);
}
