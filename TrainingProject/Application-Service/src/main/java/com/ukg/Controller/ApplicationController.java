package com.ukg.Controller;

import com.ukg.dto.ApplicationDTO;
import com.ukg.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Get all applications (for HR/Admin)
    @GetMapping("/all")
    public ResponseEntity<List<ApplicationDTO>> getAllApplications(@RequestParam(required = false) String role) {
        if (role != null && role.equalsIgnoreCase("hr")) {
            return ResponseEntity.ok(applicationService.getAllApplications());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Forbidden for non-HR users
        }
    }

    // Get all applications for a specific opening (Only for HR/Admin)
    @GetMapping("/opening/{openingId}")
    public ResponseEntity<List<ApplicationDTO>> getAllApplicationsForOpening(
            @PathVariable Long openingId,
            @RequestParam("role") String role) {

        if (!"hr".equalsIgnoreCase(role)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<ApplicationDTO> applications = applicationService.getAllApplicationsForOpening(openingId);
        return ResponseEntity.ok(applications);
    }

    // Get all applications for a specific employee
    @GetMapping("/employee/{empID}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByEmployee(@PathVariable Long empID) {
        List<ApplicationDTO> applications = applicationService.getApplicationsByEmployee(empID);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PostMapping("/apply")
    ResponseEntity<String> applyForOpening(@RequestBody ApplicationDTO applicationDTO){
        applicationService.applyForOpening(applicationDTO);
        return new ResponseEntity<>("Applied Successfully",HttpStatus.OK);
    }
}
