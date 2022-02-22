package com.example.gradinfo.dto.request;

import lombok.Data;

@Data
public class EventObject {
    private String eventCode;
    private String eventDescription;
    private String ncrerOper;
    private String ncrerDate;
    private String ncrerRelated;
    private String ncrerTransdate;
}
