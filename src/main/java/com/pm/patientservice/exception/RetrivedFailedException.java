package com.pm.patientservice.exception;

import com.pm.patientservice.enums.ResponseCode;

public class RetrivedFailedException extends BaseException{
    public RetrivedFailedException(ResponseCode responseCode) { super(responseCode); }
}
