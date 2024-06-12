package com.lawencon.pss_app.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lawencon.pss_app.config.SftpConfig;
import com.lawencon.pss_app.constant.RoleEnum;
import com.lawencon.pss_app.dao.DateDao;
import com.lawencon.pss_app.dao.DocumentDao;
import com.lawencon.pss_app.dao.FileDao;
import com.lawencon.pss_app.dao.UserDao;
import com.lawencon.pss_app.dto.fileops.GetFileReqDto;
import com.lawencon.pss_app.helper.FileHelper;
import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.File;
import com.lawencon.pss_app.model.Role;
import com.lawencon.pss_app.model.User;
import com.lawencon.pss_app.service.SftpService;

@Service
public class SftpServiceImpl implements SftpService {
	
    private SftpConfig sftpConfig;
	private FileDao fileDao;
	private DocumentDao documentDao;
	private UserDao userDao;
	private DateDao dateDao;
	private FileHelper fileHelper;
	
	public SftpServiceImpl(SftpConfig sftpConfig, FileDao fileDao, DocumentDao documentDao, FileHelper fileHelper, 
			UserDao userDao, DateDao dateDao) {
		this.sftpConfig = sftpConfig;
		this.fileDao = fileDao;
		this.documentDao = documentDao;
		this.fileHelper = fileHelper;
		this.userDao = userDao;
		this.dateDao = dateDao;
	}

	@Override
    public ChannelSftp setupJsch() throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(sftpConfig.getUser(), sftpConfig.getHost(), sftpConfig.getPort());
        session.setPassword(sftpConfig.getPassword());
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        return channel;
    }
    
	@Override
    @Transactional
    public void uploadFile(String localFilePath, String remoteFilePath, User user, Long dateId) throws Exception {
    	ChannelSftp channelSftp = setupJsch();
    	
//    	System.out.println(localFilePath + " " + remoteFilePath);
    	
        channelSftp.put(localFilePath, remoteFilePath);
        
        java.io.File localFile = new java.io.File(localFilePath);
        
        File file = new File();
        file.setFileName(localFile.getName());
        file.setFileExt(org.apache.commons.io.FilenameUtils.getExtension(localFilePath));  
        file.setFileBase64(fileHelper.encodeFileToBase64(localFilePath));
        fileDao.create(file);

        Document document = new Document();
        document.setUploadedBy(user);
        document.setUploadedAt(LocalDateTime.now());
        document.setFile(file);
        document.setDate(dateDao.findById(dateId));
        document.setDownloaded(false);
        documentDao.create(document);
    }

	@Override
    @Transactional
    public File downloadFile(String remoteFilePath, String localFilePath, User user) throws Exception {
        File file = null;
        ChannelSftp channelSftp = setupJsch();
        channelSftp.get(remoteFilePath, localFilePath);

        file = fileDao.findByFileName(new java.io.File(remoteFilePath).getName());
        if (file != null) {
            Document document = documentDao.findByFile(file);
            if (document != null) {
                document.setDownloaded(true);
                documentDao.create(document);
            }
        }
        return file;
    }

	@Override
    public List<Document> getDocs(GetFileReqDto req){
    	User user = userDao.findByEmail(req.getEmail());
    	List<Document> res;
    	if(user.getRole()==new Role(RoleEnum.CLIENT)) {
    		res = documentDao.findDocumentsByClient(user.getId());
    	} else {
    		res = documentDao.findDocumentsByPs(user.getId());
    	}
    	
    	return res;
    	
    }
    

}
