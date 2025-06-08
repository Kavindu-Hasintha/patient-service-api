package com.pm.patientservice.exception;

import com.pm.patientservice.enums.ResponseCode;

public class PatientSaveFailedException extends BaseException{
    public PatientSaveFailedException(ResponseCode responseCode) { super(responseCode); }
}
