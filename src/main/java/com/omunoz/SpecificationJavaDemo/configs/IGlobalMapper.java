package com.omunoz.SpecificationJavaDemo.configs;

import com.omunoz.SpecificationJavaDemo.controllers.dtos.DepartmentDto;
import com.omunoz.SpecificationJavaDemo.controllers.dtos.EmployeeDto;
import com.omunoz.SpecificationJavaDemo.entities.Department;
import com.omunoz.SpecificationJavaDemo.entities.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    Employee fromEmployeeDto(EmployeeDto dto);
    List<Employee> fromListEmployeeDto(List<EmployeeDto> lstDto);
    Department fromDepartamentDto(DepartmentDto dto);
    List<Department>  fromListDepartamentDto(List<DepartmentDto> lstDto);

}
