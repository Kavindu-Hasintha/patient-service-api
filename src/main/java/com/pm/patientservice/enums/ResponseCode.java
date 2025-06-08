package com.pm.patientservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("Success", ""),
    ERROR("Unknown Error", ""),
    INVALID_REQUEST_DATA("Invalid Request Data", ""),

    PATIENT_LIST_FAIL("Patient List Failed", ""),
    PATIENT_SAVE_FAIL("Patient Save Failed", ""),
    PATIENT_NOT_FOUND("Patient Not Found", ""),
    PATIENT_UPDATE_FAIL("Patient Update Failed", ""),
    PATIENT_DELETE_FAIL("Patient Delete Failed", "");

    private String message;
    private String reason;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
