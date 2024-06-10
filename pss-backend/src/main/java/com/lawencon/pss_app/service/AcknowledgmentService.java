package com.lawencon.pss_app.service;

import com.lawencon.pss_app.dto.ackno.AcknowledgmentReqDto;
import com.lawencon.pss_app.model.Acknowledgment;

public interface AcknowledgmentService {

	Acknowledgment createAcknowledgment(AcknowledgmentReqDto reqDto);

}
