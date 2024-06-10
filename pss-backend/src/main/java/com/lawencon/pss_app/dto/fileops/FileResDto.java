package com.lawencon.pss_app.dto.fileops;

public class FileResDto {
    private String message;
    private boolean success;

    public FileResDto() {}

    public FileResDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
