package com.ssafy.zip.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }

    public ErrorResponse(ErrorCode errorCode, Exception e){
        this.status = errorCode.getStatus();
        this.message = e.getMessage();
        this.code = errorCode.getErrorCode();
    }
}
