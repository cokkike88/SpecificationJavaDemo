package com.omunoz.SpecificationJavaDemo.data.repositories;

import com.omunoz.SpecificationJavaDemo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentRepository extends JpaRepository<Department, Long> {
}
