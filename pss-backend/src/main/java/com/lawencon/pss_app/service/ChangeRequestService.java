package com.lawencon.pss_app.service;

import com.lawencon.pss_app.dto.reqchange.EventReqDto;
import com.lawencon.pss_app.dto.reqchange.EventResDto;
import com.lawencon.pss_app.dto.reqchange.GetChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.GetChangeResDto;
import com.lawencon.pss_app.dto.reqchange.ReqChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.ReqChangeResDto;
import com.lawencon.pss_app.dto.reqchange.UpdateChangeReqDto;
import com.lawencon.pss_app.dto.reqchange.UpdateChangeResDto;

public interface ChangeRequestService {

	ReqChangeResDto processChangeRequest(ReqChangeReqDto req);

	EventResDto getEvents(EventReqDto req);

	UpdateChangeResDto updateReqChange(UpdateChangeReqDto req);

	GetChangeResDto getChangeByType(GetChangeReqDto req);

}
