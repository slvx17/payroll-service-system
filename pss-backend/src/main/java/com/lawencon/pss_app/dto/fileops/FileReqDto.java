package com.lawencon.pss_app.dto.fileops;

public class FileReqDto {
    private String username;
    private String filePath;

    public FileReqDto() {}

    public FileReqDto(String username, String filePath) {
        this.username = username;
        this.filePath = filePath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
