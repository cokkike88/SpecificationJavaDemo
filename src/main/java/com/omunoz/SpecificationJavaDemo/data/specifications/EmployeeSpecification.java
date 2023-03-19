package com.omunoz.SpecificationJavaDemo.data.specifications;

import com.omunoz.SpecificationJavaDemo.data.criterias.SearchCriteria;
import com.omunoz.SpecificationJavaDemo.entities.Department;
import com.omunoz.SpecificationJavaDemo.entities.Employee;
import com.omunoz.SpecificationJavaDemo.utils.SearchOperation;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class EmployeeSpecification implements Specification<Employee> {

    private final SearchCriteria searchCriteria;

    public EmployeeSpecification(SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String strToSearch = this.searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case CONTAINS -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.like(criteriaBuilder.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            }
            case DOES_NOT_CONTAIN -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.notLike(criteriaBuilder.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return criteriaBuilder.notLike(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            }
            case BEGINS_WITH -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.like(criteriaBuilder.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");
            }
            case DOES_NOT_BEGIN_WITH -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.notLike(criteriaBuilder.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return criteriaBuilder.notLike(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");
            }
            case ENDS_WITH -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.like(criteriaBuilder.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), strToSearch);
            }
            case DOES_NOT_END_WITH -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.notLike(criteriaBuilder.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return criteriaBuilder.notLike(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);
            }
            case EQUAL -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    System.out.println(searchCriteria.getValue());
                    return criteriaBuilder.equal(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                return criteriaBuilder.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());
            }
            case NOT_EQUAL -> {
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return criteriaBuilder.notEqual(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue() );
                }
                return criteriaBuilder.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());
            }
            case NUL -> {
                return criteriaBuilder.isNull(root.get(searchCriteria.getFilterKey()));
            }
            case NOT_NULL -> {
                return criteriaBuilder.isNotNull(root.get(searchCriteria.getFilterKey()));
            }
            case GREATER_THAN -> {
                return criteriaBuilder.greaterThan(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
            }
            case GREATER_THAN_EQUAL -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
            }
            case LESS_THAN -> {
                return criteriaBuilder.lessThan(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
            }
            case LESS_THAN_EQUAL -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
            }
        }
        return null;
    }

    private Join<Employee, Department> departmentJoin(Root<Employee> root){
        return root.join("department");
    }
}
