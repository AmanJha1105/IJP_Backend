package com.ukg.service;

import com.ukg.FeignClient.JobClient;
import com.ukg.dto.OpeningDTO;
import com.ukg.model.JobModel;
import com.ukg.model.OpeningModel;
import com.ukg.repository.JobRepository;
import com.ukg.repository.OpeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OpeningService {

    @Autowired
    private OpeningRepository openingRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobClient jobClient;

    public OpeningDTO convertToDTO(OpeningModel opening) {
        OpeningDTO dto = new OpeningDTO();
        dto.setJobId(opening.getJob().getJobId());
        dto.setOpeningId(opening.getOpeningId());
        dto.setLastDateToApply(opening.getLastDateToApply());
        dto.setExperience(opening.getExperience());
        dto.setLocation(opening.getLocation());
        dto.setTitle(opening.getTitle());
        dto.setSkills(opening.getSkills());
        dto.setDescription(opening.getDescription());
        dto.setSalary(opening.getSalary());
        return dto;
    }

    public List<OpeningDTO> getAllOpenings() {
        List<OpeningModel> openings = openingRepository.findAll();
        return openings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OpeningDTO> getOpeningById(Long openingId) {
        return openingRepository.findById(openingId).map(this::convertToDTO);
    }

    public ResponseEntity<OpeningDTO> addOpening(OpeningDTO opening) {
        // Ensure job exists
        System.out.println("here2");
        System.out.println(opening);
        System.out.println(opening.getJobId());

        JobModel job = jobClient.getJobById(opening.getJobId());

        // Update job reference

        System.out.println("here3");

        OpeningModel o = new OpeningModel();

        o.setJob(job);
        o.setLocation(opening.getLocation());
        o.setTitle(opening.getTitle());
        o.setExperience(opening.getExperience());
        o.setSkills(opening.getSkills());
        o.setLastDateToApply(opening.getLastDateToApply());
        o.setSalary(opening.getSalary());
        o.setLocation(opening.getLocation());

        // Save the opening model without applications
        openingRepository.save(o);

        return new ResponseEntity<>(opening,HttpStatus.CREATED);
    }

    public Optional<OpeningModel> updateOpening(Long openingId, OpeningModel updatedOpening) {
        return Optional.ofNullable(openingRepository.findById(openingId).map(opening -> {
            opening.setDescription(updatedOpening.getDescription() != null ? updatedOpening.getDescription() : opening.getDescription());
            opening.setJob(updatedOpening.getJob() != null ? updatedOpening.getJob() : opening.getJob());
            opening.setLastDateToApply(updatedOpening.getLastDateToApply() != null ? updatedOpening.getLastDateToApply() : opening.getLastDateToApply());
            opening.setLocation(updatedOpening.getLocation() != null ? updatedOpening.getLocation() : opening.getLocation());
            opening.setTitle(updatedOpening.getTitle() != null ? updatedOpening.getTitle() : opening.getTitle());
            opening.setSkills(updatedOpening.getSkills() != null ? updatedOpening.getSkills() : opening.getSkills());
            opening.setSalary(updatedOpening.getSalary() != null ? updatedOpening.getSalary() : opening.getSalary());
            opening.setExperience(updatedOpening.getExperience() != null ? updatedOpening.getExperience() : opening.getExperience());

            return openingRepository.save(opening);
        }).orElseThrow(() -> new RuntimeException("Opening not found with id " + openingId)));
    }

    public void deleteOpening(Long openingId) {
        openingRepository.deleteById(openingId);
    }
}
