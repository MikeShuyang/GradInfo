package com.example.gradinfo.dto.response;

import lombok.Data;

@Data
public class StudentPostResponse {
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
    private String spThesisTitle;
}
