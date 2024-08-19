package com.ukg.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OpeningDTO {
    private Long openingId;
    private Long jobId;
    private String description;
    private String location;
    private String lastDateToApply;
    private String title;
    private String skills;
    private String salary;
    private String experience;
}
