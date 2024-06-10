package com.lawencon.pss_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.pss_app.dto.ackno.AcknowledgmentReqDto;
import com.lawencon.pss_app.dto.ackno.AcknowledgmentResDto;
import com.lawencon.pss_app.model.Acknowledgment;
import com.lawencon.pss_app.service.AcknowledgmentService;

@RestController
@RequestMapping("/signature")
public class AcknowledgmentController {

	@Autowired
    private AcknowledgmentService acknowledgmentService;

	@PostMapping("/createacknowledgment")
    public ResponseEntity<AcknowledgmentResDto> createAcknowledgment(@RequestBody AcknowledgmentReqDto reqDto) {
        try {
            Acknowledgment acknowledgment = acknowledgmentService.createAcknowledgment(reqDto);
            return ResponseEntity.ok(new AcknowledgmentResDto(acknowledgment.getId(),"success"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
