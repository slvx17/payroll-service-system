package com.lawencon.pss_app.dto.fileops;

import java.util.List;

public class GetFileResDto {
	List<Long> docIds;
	List<String> docNames;
	
	public GetFileResDto(List<Long> docIds, List<String> docNames) {

		this.docIds = docIds;
		this.docNames = docNames;
	}

	public List<Long> getDocIds() {
		return docIds;
	}

	public void setDocIds(List<Long> docIds) {
		this.docIds = docIds;
	}

	public List<String> getDocNames() {
		return docNames;
	}

	public void setDocNames(List<String> docNames) {
		this.docNames = docNames;
	}
	
	
	
	
}
