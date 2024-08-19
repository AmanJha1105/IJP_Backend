package com.ukg.controller;


import com.ukg.dto.LoginDTO;
import com.ukg.dto.RegisterDTO;
import com.ukg.exception.AuthenticationException;
import com.ukg.model.EmployeeModel;
import com.ukg.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/employee")
public class AuthenticationController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<EmployeeModel> register(@RequestBody RegisterDTO req){
        try{
            return new ResponseEntity(authService.register(req),HttpStatus.OK);

        }
        catch(AuthenticationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(path="/login")
    public ResponseEntity<EmployeeModel> login(@RequestBody LoginDTO req){
        try{
            return new ResponseEntity(authService.login(req),HttpStatus.OK);
        }
        catch(AuthenticationException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}

