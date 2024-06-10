package com.lawencon.pss_app.service;

import java.util.List;

import com.jcraft.jsch.ChannelSftp;
import com.lawencon.pss_app.dto.fileops.GetFileReqDto;
import com.lawencon.pss_app.model.Document;
import com.lawencon.pss_app.model.File;
import com.lawencon.pss_app.model.User;

public interface SftpService {

	ChannelSftp setupJsch() throws Exception;

	void uploadFile(String localFilePath, String remoteFilePath, User user) throws Exception;

	File downloadFile(String remoteFilePath, String localFilePath, User user) throws Exception;

	List<Document> getDocs(GetFileReqDto req);

}
