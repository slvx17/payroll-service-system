package com.lawencon.pss_app.dto.reqchange;

import java.util.List;

import com.lawencon.pss_app.model.ChangeRequest;

public class GetChangeResDto {
	
	List<ChangeRequest> reqRes;
	
	public GetChangeResDto(List<ChangeRequest> reqRes) {
		this.reqRes = reqRes;
	}

	public List<ChangeRequest> getReqRes() {
		return reqRes;
	}

	public void setReqRes(List<ChangeRequest> reqRes) {
		this.reqRes = reqRes;
	}
	
	

}
