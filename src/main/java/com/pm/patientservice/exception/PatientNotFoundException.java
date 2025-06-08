package com.pm.patientservice.exception;

import com.pm.patientservice.enums.ResponseCode;

public class PatientNotFoundException extends BaseException {
    public PatientNotFoundException(ResponseCode responseCode) { super(responseCode); }
}
