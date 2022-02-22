package com.example.gradinfo.dto.response;

import lombok.Data;

@Data
public class DegreeCheckResponse {
    private String degreeName;
    private String degreeAdmissionTerm;
    private String degreeGraduationTerm;
    private String degreeCatalogYear;
    private String degreeForeignLanguage;
    private boolean degreeCheckCompleted;
}
