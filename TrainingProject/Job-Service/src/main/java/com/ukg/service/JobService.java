package com.ukg.service;

import com.ukg.FeignClient.OpeningClient;
import com.ukg.dto.OpeningDTO;
import com.ukg.exceptions.ResourceNotFoundException;
import com.ukg.model.JobModel;
import com.ukg.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private OpeningClient openingClient;

    public JobModel addJob(JobModel jobModel) {
        try {
            return jobRepository.save(jobModel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return jobModel;
        }
    }

    public List<JobModel> getAllJobs() {
        return jobRepository.findAll();
    }

    public OpeningDTO getOpeningById(Long openingId) {

        return openingClient.getOpeningById(openingId);
    }

    public JobModel getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));
    }
}
