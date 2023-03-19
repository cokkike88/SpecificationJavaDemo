package com.omunoz.SpecificationJavaDemo.data.specifications;

import com.omunoz.SpecificationJavaDemo.model.CompanyModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CompanyModelSpecification implements Specification<CompanyModel> {
    @Override
    public Predicate toPredicate(Root<CompanyModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
