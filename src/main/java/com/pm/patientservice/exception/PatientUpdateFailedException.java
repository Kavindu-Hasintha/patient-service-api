package com.pm.patientservice.exception;

import com.pm.patientservice.enums.ResponseCode;

public class PatientUpdateFailedException extends BaseException {
    public PatientUpdateFailedException(ResponseCode responseCode) { super(responseCode); }
}
