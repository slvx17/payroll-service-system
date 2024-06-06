package com.lawencon.pss_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lawencon.pss_app.constant.RequestStatusTypeEnum;

@Entity
@Table(name = "request_status_type")
public class RequestStatusType {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_name", nullable = false, length = 50)
    private String requestName;

    @Column(name = "request_code", nullable = false, length = 5)
    private String requestCode;

    public RequestStatusType(RequestStatusTypeEnum reqstatenum) {
    	this.requestName = reqstatenum.getRequestName();
    	this.requestCode = reqstatenum.getRequestCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

    
}