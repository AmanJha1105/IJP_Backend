package com.ukg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name="job")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String description;

    private double salary;

    @JsonIgnore
    @OneToOne(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OpeningModel opening;
}
