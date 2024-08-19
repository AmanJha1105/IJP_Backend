package com.ukg.FeignClient;

import com.ukg.dto.ApplicationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "application-service") // The name of the application service in Eureka
public interface ApplicationClient {

    @GetMapping("/api/applications/employee/{empId}")
    List<ApplicationDTO> getApplicationsByEmployeeId(@PathVariable("empId") Long empId);

    @PostMapping("/api/applications/apply")
    ResponseEntity<String> applyForOpening(@RequestBody ApplicationDTO applicationDTO);
}
