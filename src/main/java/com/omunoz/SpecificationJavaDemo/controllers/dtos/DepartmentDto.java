package com.omunoz.SpecificationJavaDemo.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omunoz.SpecificationJavaDemo.entities.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class DepartmentDto {

    private Long deptId;
    private String deptName;
    private List<EmployeeDto> employees;
}
