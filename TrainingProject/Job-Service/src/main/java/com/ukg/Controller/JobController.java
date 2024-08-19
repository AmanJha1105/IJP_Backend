package com.ukg.Controller;

import com.ukg.dto.OpeningDTO;
import com.ukg.model.JobModel;
import com.ukg.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<List<JobModel>> getAllJobs() {
        try {
            List<JobModel> jobs = jobService.getAllJobs();
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<JobModel> addJob(@RequestBody JobModel jobModel) {
        try {
            JobModel createdJob = jobService.addJob(jobModel);
            return new ResponseEntity<>(createdJob, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{jobId}")
    JobModel getJobById(@PathVariable("jobId") Long jobId){
        return jobService.getJobById(jobId);
    }

    @GetMapping("/opening/{id}")
    public ResponseEntity<OpeningDTO> getOpeningById(@PathVariable Long id) {
        OpeningDTO opening = jobService.getOpeningById(id);
        return opening != null ? ResponseEntity.ok(opening) : ResponseEntity.notFound().build();
    }
}
