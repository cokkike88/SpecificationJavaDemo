package com.omunoz.SpecificationJavaDemo.controllers;

import com.omunoz.SpecificationJavaDemo.controllers.dtos.DepartmentDto;
import com.omunoz.SpecificationJavaDemo.controllers.dtos.EmployeeDto;
import com.omunoz.SpecificationJavaDemo.controllers.dtos.EmployeeSearchDto;
import com.omunoz.SpecificationJavaDemo.data.criterias.SearchCriteria;
import com.omunoz.SpecificationJavaDemo.data.specifications.EmpSpecificationBuilder;
import com.omunoz.SpecificationJavaDemo.entities.Employee;
import com.omunoz.SpecificationJavaDemo.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/v1")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Object> save(@RequestBody EmployeeDto employeeDto){
        try{
            employeeService.save(employeeDto);
            return ResponseEntity.accepted().body("Employee saved");
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(String.format("Error to save the employee: %s", ex.getMessage()));
        }
    }

    @PostMapping("/departament")
    public ResponseEntity<Object> save(@RequestBody DepartmentDto departmentDto){
        try{
            var response = employeeService.saveDepartament(departmentDto);
            return ResponseEntity.accepted().body(response);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(String.format("Error to save the department: %s", ex.getMessage()));
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> save(@RequestBody List<EmployeeDto> employeeDtos){
        try{
            var response = employeeService.save(employeeDtos);
            return ResponseEntity.accepted().body(response);
        }
        catch (Exception ex){
            return ResponseEntity.internalServerError().body(String.format("Error to save the department: %s", ex.getMessage()));
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchEmployees(
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestBody EmployeeSearchDto employeeSearchDto
            ){
        var builder = new EmpSpecificationBuilder();
        List<SearchCriteria> criteriaList = employeeSearchDto.getSearchCriteriaList();
        if(criteriaList != null){
            criteriaList.stream().forEach(x -> {
                x.setDataOption(employeeSearchDto.getDataOption());
                builder.with(x);
            });
        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("empfirstNm")
                .ascending()
                .and(Sort.by("emplastNm"))
                .ascending()
                .and(Sort.by("department"))
                .ascending());

        Page<Employee> employeePage = employeeService.findBySearchCriteria(builder.build(), page);
        return new ResponseEntity<>(employeePage.toList(), HttpStatus.OK);
    }


}
