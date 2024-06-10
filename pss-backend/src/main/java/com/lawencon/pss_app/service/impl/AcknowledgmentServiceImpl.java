package com.lawencon.pss_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.pss_app.dao.AcknowledgmentDao;
import com.lawencon.pss_app.dao.DocumentDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.ackno.AcknowledgmentReqDto;
import com.lawencon.pss_app.model.Acknowledgment;
import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.AcknowledgmentService;

@Service
public class AcknowledgmentServiceImpl implements AcknowledgmentService {
	
	@Autowired
    private AcknowledgmentDao acknowledgmentDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private UserDao userDao;

    @Transactional
    @Override
    public Acknowledgment createAcknowledgment(AcknowledgmentReqDto reqDto) {
        Document document = documentDao.findById(reqDto.getDocumentId());
        User user = userDao.findById(reqDto.getUserId());

        Acknowledgment acknowledgment = new Acknowledgment();
        acknowledgment.setDocument(document);
        acknowledgment.setUser(user);
        acknowledgment.setIsSender(reqDto.getIsSender());
        acknowledgment.setSigned(false); // Assuming a signature is not done at creation
        acknowledgment.setSignedAt(reqDto.getSignedAt());

        return acknowledgmentDao.create(acknowledgment);
    }
	
}
