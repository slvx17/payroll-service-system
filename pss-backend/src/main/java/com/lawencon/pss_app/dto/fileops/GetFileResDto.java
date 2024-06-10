package com.lawencon.pss_app.dto.fileops;

import java.util.List;

import com.lawencon.pss_app.model.Document;

public class GetFileResDto {
	List<Document> documents;

	public GetFileResDto(List<Document> documents) {
		this.documents = documents;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	
}
