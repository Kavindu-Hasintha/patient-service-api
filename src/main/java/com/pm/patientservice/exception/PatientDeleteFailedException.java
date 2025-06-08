package com.pm.patientservice.exception;

import com.pm.patientservice.enums.ResponseCode;

public class PatientDeleteFailedException extends BaseException {
    public PatientDeleteFailedException(ResponseCode responseCode) { super(responseCode); }
}
