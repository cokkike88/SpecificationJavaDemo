package com.omunoz.SpecificationJavaDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "emp_last_name")
    private String emplastNm;

    @Column(name = "emp_name")
    private String empfirstNm;

    @Column(name = "job_name")
    private String job;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "hire_date")
    private Date hireDate;

    @Column(name = "salary")
    private double salary;

    @Column(name = "commission")
    private double commission;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;
}
