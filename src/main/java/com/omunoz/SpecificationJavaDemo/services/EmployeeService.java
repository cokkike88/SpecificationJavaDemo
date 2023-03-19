package com.omunoz.SpecificationJavaDemo.services;

import com.omunoz.SpecificationJavaDemo.configs.IGlobalMapper;
import com.omunoz.SpecificationJavaDemo.controllers.dtos.DepartmentDto;
import com.omunoz.SpecificationJavaDemo.controllers.dtos.EmployeeDto;
import com.omunoz.SpecificationJavaDemo.data.repositories.IDepartamentRepository;
import com.omunoz.SpecificationJavaDemo.data.repositories.IEmployeeRepository;
import com.omunoz.SpecificationJavaDemo.entities.Department;
import com.omunoz.SpecificationJavaDemo.entities.Employee;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IDepartamentRepository departamentRepository;
    private final IGlobalMapper globalMapper;

    @Transactional
    public void save(EmployeeDto dto){
        var employee = globalMapper.fromEmployeeDto(dto);
        employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> save(List<EmployeeDto> lstDto){
        var employees = globalMapper.fromListEmployeeDto(lstDto);
        var response = employeeRepository.saveAll(employees);
        return response;
    }

    public List<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }

    public Page<Employee> findBySearchCriteria(Specification<Employee> espec, Pageable page){
        Page<Employee> searchResult = employeeRepository.findAll(espec, page);
        return searchResult;
    }

    @Transactional
    public Department saveDepartament(DepartmentDto departmentDto) {
        var departament = globalMapper.fromDepartamentDto(departmentDto);
        var response = departamentRepository.save(departament);
        return response;
    }

}
