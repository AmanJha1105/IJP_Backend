package com.ukg.service;

import com.ukg.FeignClient.EmployeeClient;
import com.ukg.FeignClient.OpeningClient;
import com.ukg.dto.ApplicationDTO;
import com.ukg.dto.EmployeeDTO;
import com.ukg.dto.OpeningDTO;
import com.ukg.model.ApplicationsModel;
import com.ukg.model.EmployeeModel;
import com.ukg.model.OpeningModel;
//import com.ukg.repository.ApplicationDao;
import com.ukg.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private OpeningClient openingClient;

    public List<ApplicationDTO> getAllApplications() {
        List<ApplicationsModel> applications = applicationRepository.findAll();
        return applications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ApplicationDTO> getAllApplicationsForOpening(Long openingId) {
        List<ApplicationsModel> applications = applicationRepository.findByOpening_OpeningId(openingId);
        return applications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ApplicationDTO> getApplicationsByEmployee(Long empID) {
        List<ApplicationsModel> applications = applicationRepository.findByEmployee_empID(empID);
        return applications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ApplicationDTO convertToDTO(ApplicationsModel application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(application.getApplicationId());
        dto.setOpeningId(application.getOpening().getOpeningId());
        dto.setEmpId(application.getEmployee().getEmpID());
        dto.setEmpName(application.getEmployee().getEmpName());
        dto.setEmpRole(application.getEmployee().getEmpRole());
        return dto;
    }

    public void applyForOpening(ApplicationDTO applicationDTO) {
        // Fetch the employee and opening entities
        EmployeeDTO employee = employeeClient.getEmployeeById(applicationDTO.getEmpId());
        OpeningDTO opening = openingClient.getOpeningById(applicationDTO.getOpeningId());

        // Map DTO to entity
        ApplicationsModel application = new ApplicationsModel();
        EmployeeModel model = new EmployeeModel();
        model.setEmpID(employee.getEmpID());
        model.setEmpName(employee.getEmpName());
        model.setEmpRole(employee.getEmpRole());
        application.setEmployee(model);

        OpeningModel o = new OpeningModel();
        o.setOpeningId(opening.getOpeningId());
        o.setTitle(opening.getTitle());
        o.setDescription(opening.getDescription());

        application.setOpening(o);

        // Save the application
        applicationRepository.save(application);
    }


}
