package com.ukg.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String empName;
    private String empRole;
    private Boolean isAdmin;

    private String username;
    private String password;
}
