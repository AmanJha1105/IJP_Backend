package com.ukg.Controller;

import com.ukg.dto.OpeningDTO;
import com.ukg.model.OpeningModel;
import com.ukg.service.OpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/openings")
@CrossOrigin("*")
public class OpeningController {

    @Autowired
    private OpeningService openingService;

    // Get all openings
    @GetMapping("/all")
    public ResponseEntity<List<OpeningDTO>> getAllOpenings() {
        List<OpeningDTO> openingDTOs = openingService.getAllOpenings();
        return ResponseEntity.ok(openingDTOs);
    }

    // Get opening by ID
    @GetMapping("/{id}")
    public ResponseEntity<OpeningDTO> getOpeningById(@PathVariable Long id) {
        Optional<OpeningDTO> opening = openingService.getOpeningById(id);
        return opening.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Add a new opening
    @PostMapping("/add")
    public ResponseEntity<OpeningDTO> addOpening(@RequestBody OpeningDTO opening) {
        try {
            System.out.println("here 1");
            OpeningDTO createdOpening = openingService.addOpening(opening).getBody();
            return new ResponseEntity<>(createdOpening, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing opening
    @PatchMapping("/update/{id}")
    public ResponseEntity<OpeningDTO> updateOpening(@PathVariable("id") Long id, @RequestBody OpeningModel updatedOpening) {
        try {
            Optional<OpeningModel> opening = openingService.updateOpening(id, updatedOpening);
            if (opening != null) {
                return opening.map(o -> {
                    OpeningDTO dto = new OpeningDTO();
                    dto.setOpeningId(o.getOpeningId());
                    dto.setDescription(o.getDescription());
                    dto.setLocation(o.getLocation());
                    dto.setLastDateToApply(o.getLastDateToApply());
                    dto.setTitle(o.getTitle());
                    dto.setSkills(o.getSkills());
                    dto.setSalary(o.getSalary());
                    dto.setExperience(o.getExperience());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete an opening
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpening(@PathVariable Long id) {
        openingService.deleteOpening(id);
        return ResponseEntity.ok("Delete successful");
    }
}
