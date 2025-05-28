package com.pm.patientservice.dtos;

import com.pm.patientservice.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {
    protected int code = ResponseCode.SUCCESS.ordinal();
    protected String message;
    protected String reason;
    protected Object data;

    public static APIResponseDto getInstance(ResponseCode responseCode){
        APIResponseDto apiResponseDto = new APIResponseDto(responseCode.ordinal(),responseCode.getMessage(),responseCode.getReason(),null);
        return apiResponseDto;
    }
    public static APIResponseDto getInstance(ResponseCode responseCode,Object obj){
        APIResponseDto apiResponseDto = new APIResponseDto(responseCode.ordinal(),responseCode.getMessage(),responseCode.getReason(),obj);
        return apiResponseDto;
    }
}
