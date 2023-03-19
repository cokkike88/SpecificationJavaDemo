package com.omunoz.SpecificationJavaDemo.controllers.dtos;

import com.omunoz.SpecificationJavaDemo.data.criterias.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchDto {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
