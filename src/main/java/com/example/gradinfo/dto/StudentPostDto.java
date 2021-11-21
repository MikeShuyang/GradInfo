package com.example.gradinfo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentPostDto {
    private String spObj;
    private String spMajor;
    private String spNdocs;
    private String spConfu;
    private String spAdmit;
    private String spReadmit;
    private String spTransdate;
    private double spEarnunits;
    private double spRgunits;
    private double spGpaApply;
    private double spGpaAll;

}
