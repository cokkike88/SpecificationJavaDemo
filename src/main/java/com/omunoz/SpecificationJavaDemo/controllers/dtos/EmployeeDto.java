package com.omunoz.SpecificationJavaDemo.controllers.dtos;

import com.omunoz.SpecificationJavaDemo.entities.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class EmployeeDto {

    private Long empId;

    private String emplastNm;

    private String empfirstNm;

    private String job;

    private Long managerId;

    private Date hireDate;

    private double salary;

    private double commission;

    private DepartmentDto department;
}
