package com.roger.git.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * Custom api response
 */
public class ApiCustomResponse {
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String message;

    public ApiCustomResponse(){}
    public ApiCustomResponse(LocalDateTime timestamp, int status, String message){
        this.timestamp=timestamp;
        this.status=status;
        this.message =message;

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
