package com.omunoz.SpecificationJavaDemo.data.specifications;

import com.omunoz.SpecificationJavaDemo.data.criterias.SearchCriteria;
import com.omunoz.SpecificationJavaDemo.entities.Employee;
import com.omunoz.SpecificationJavaDemo.utils.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmpSpecificationBuilder {

    private List<SearchCriteria> params;

    public EmpSpecificationBuilder (){
        this.params = new ArrayList<>();
    }

    public final EmpSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final EmpSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Employee> build(){
        if(params.size() == 0) return null;

        Specification<Employee> result = new EmployeeSpecification(params.get(0));
        for(int i = 1; i < params.size(); i++){
            var criteria = params.get(i);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL?
                    Specification.where(result).and(new EmployeeSpecification(criteria)):
                    Specification.where(result).or(new EmployeeSpecification(criteria));
        }

        return result;
    }
}
