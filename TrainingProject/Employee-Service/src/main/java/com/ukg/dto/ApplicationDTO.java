package com.ukg.dto;

import lombok.Data;

@Data
public class ApplicationDTO {
    private Long applicationId;
    private Long openingId;
    private String empName;
    private Long empId;
    private String empRole;
    private String title;
    private String location;
    private String experience;

}
