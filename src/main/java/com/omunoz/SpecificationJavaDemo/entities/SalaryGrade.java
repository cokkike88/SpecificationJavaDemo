package com.omunoz.SpecificationJavaDemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salary_grade")
public class SalaryGrade {

    @Id
    private Long grade;
    private double min_salary;
    private double max_salary;
}
