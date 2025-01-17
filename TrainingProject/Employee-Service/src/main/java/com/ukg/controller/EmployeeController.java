package com.ukg.controller;


import com.ukg.dto.ApplicationDTO;
import com.ukg.dto.EmployeeDTO;
import com.ukg.exception.EmployeeExceptions;
import com.ukg.model.EmployeeModel;
import com.ukg.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path="/all")
    public ResponseEntity<List<EmployeeModel>>getAllEmployees(){
        try{
            return new ResponseEntity<List<EmployeeModel>>(employeeService.getAllEmployees(),HttpStatus.OK);
        }
        catch(EmployeeExceptions e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId)
                .map(employee -> ResponseEntity.ok(employeeService.convertToDTO(employee)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{empId}/applications")
    public ResponseEntity<List<ApplicationDTO>> getEmployeeApplications(@PathVariable Long empId) {
        List<ApplicationDTO> applications = employeeService.getEmployeeApplications(empId);
        if (applications != null) {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<EmployeeModel> addEmployee(@RequestBody EmployeeModel employee) {
        try {
            EmployeeModel createdEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{employeeId}/apply/{openingId}")
    public ResponseEntity<String> applyForOpening(@PathVariable Long employeeId, @PathVariable Long openingId) {
        return employeeService.applyForOpening(employeeId, openingId);
    }
}