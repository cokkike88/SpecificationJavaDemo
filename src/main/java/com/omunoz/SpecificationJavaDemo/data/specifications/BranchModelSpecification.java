package com.omunoz.SpecificationJavaDemo.data.specifications;

import com.omunoz.SpecificationJavaDemo.data.criterias.SearchCriteria;
import com.omunoz.SpecificationJavaDemo.model.BranchModel;
import com.omunoz.SpecificationJavaDemo.model.CompanyModel;
import com.omunoz.SpecificationJavaDemo.utils.SearchOperation;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class BranchModelSpecification implements Specification<BranchModel> {

    private final SearchCriteria searchCriteria;

    public BranchModelSpecification(SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;

    }

    @Override
    public Predicate toPredicate(Root<BranchModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var strToSearch = this.searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case CONTAINS -> {
                if(searchCriteria.getFilterKey().equals("compName")){
                    return criteriaBuilder.like(criteriaBuilder.lower(companyModelJoin(root).get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            }
        }
        return null;
    }

    public Join<BranchModel, CompanyModel> companyModelJoin(Root<BranchModel> root){
        return root.join("company");
    }
}
