package com.ukg.service;

import com.ukg.FeignClient.ApplicationClient;
import com.ukg.FeignClient.OpeningClient;
import com.ukg.dto.ApplicationDTO;
import com.ukg.dto.EmployeeDTO;
import com.ukg.dto.OpeningDTO;
import com.ukg.exception.EmployeeExceptions;
import com.ukg.model.EmployeeModel;
import com.ukg.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ApplicationClient applicationClient;

    @Autowired
    private OpeningClient openingClient;


    public EmployeeDTO convertToDTO(EmployeeModel employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmpID(employee.getEmpID());
        dto.setEmpName(employee.getEmpName());
        dto.setEmpRole(employee.getEmpRole());
        dto.setIsAdmin(employee.getIsAdmin());
        dto.setUsername(employee.getUsername());
        dto.setPassword(employee.getPassword());

        // Fetch applications using Feign Client
//        List<ApplicationDTO> applicationDTOs = applicationClient.getApplicationsByEmployeeId(employee.getEmpID());
//        dto.setApplications(applicationDTOs);

        return dto;
    }

    public ResponseEntity<String> applyForOpening(Long employeeId, Long openingId) {
        // Validate if employee exists
        EmployeeModel employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Validate if opening exists using Feign client
        OpeningDTO opening = openingClient.getOpeningById(openingId);
        if (opening == null) {
            throw new RuntimeException("Opening not found");
        }

        // Prepare ApplicationDTO to send to the Application service
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setEmpId(employee.getEmpID());
        applicationDTO.setEmpName(employee.getEmpName());
        applicationDTO.setEmpRole(employee.getEmpRole());
        applicationDTO.setOpeningId(openingId);
        applicationDTO.setTitle(opening.getTitle());
        applicationDTO.setExperience(opening.getExperience());
        applicationDTO.setLocation(opening.getLocation());
        // Save the application using Feign client
        applicationClient.applyForOpening(applicationDTO);

        return ResponseEntity.ok("Application submitted successfully.");
    }

    public EmployeeModel addEmployee(EmployeeModel employee) {
        // Save the employee without applications
        return employeeRepository.save(employee);
    }

    public Optional<EmployeeModel> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<EmployeeModel> getAllEmployees() {
        if (employeeRepository.findAll().isEmpty()) {
            throw new EmployeeExceptions("List is empty");
        }
        return employeeRepository.findAll();
    }

    public List<ApplicationDTO> getEmployeeApplications(Long empId) {
        // Fetch applications using Feign Client
        return applicationClient.getApplicationsByEmployeeId(empId);
    }
}
