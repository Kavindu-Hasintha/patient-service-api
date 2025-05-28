package com.pm.patientservice.exception;

import com.pm.patientservice.enums.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BaseException extends Exception {
    private ResponseCode responseCode;

    public BaseException(ResponseCode responseCode) { this.responseCode = responseCode; }
}
