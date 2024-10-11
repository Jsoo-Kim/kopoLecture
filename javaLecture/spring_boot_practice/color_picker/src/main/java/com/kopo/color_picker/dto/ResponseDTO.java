package com.kopo.color_picker.dto;

public class ResponseDTO {
    private String status;
    private String message;

    public ResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

