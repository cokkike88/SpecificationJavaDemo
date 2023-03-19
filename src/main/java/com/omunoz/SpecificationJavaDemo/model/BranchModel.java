package com.omunoz.SpecificationJavaDemo.model;

import lombok.Data;

@Data
public class BranchModel {
    private Integer branId;
    private String branName;
    private CompanyModel company;
}
