package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class DegreeCheckObject {
    private String degreeCheckId;
    private String degreeName;
    private String degreeAdmissionTerm;
    private String degreeGraduationTerm;
    private String degreeCatalogYear;
    private String degreeForeignLanguage;
}
