package com.lawencon.pss_app.dto.reqchange;

public class ReqChangeResDto {
    private Long reqChangeId;
    private String message;
    
    

    public ReqChangeResDto(Long reqChangeId, String message) {
		super();
		this.reqChangeId = reqChangeId;
		this.message = message;
	}

	public Long getReqChangeId() {
        return reqChangeId;
    }

    public void setReqChangeId(Long reqChangeId) {
        this.reqChangeId = reqChangeId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}