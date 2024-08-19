package com.ukg.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="application")
public class ApplicationsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeModel employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opening_id", nullable = false)
    private OpeningModel opening;
}