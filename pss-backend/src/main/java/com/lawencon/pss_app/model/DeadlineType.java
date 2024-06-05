package com.lawencon.pss_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lawencon.pss_app.constant.DeadlineTypeEnum;

@Entity
@Table(name = "deadline_type")
public class DeadlineType {
	
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deadline_name", nullable = false, length = 50)
    private String deadlineName;

    @Column(name = "deadline_code", nullable = false, length = 5)
    private String deadlineCode;
    
    public DeadlineType() { }

    public DeadlineType(DeadlineTypeEnum deadlineTypeEnum) {
    	this.deadlineName = deadlineTypeEnum.getDeadlineName();
    	this.deadlineCode = deadlineTypeEnum.getDeadlineCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeadlineName() {
        return deadlineName;
    }

    public void setDeadlineName(String deadlineName) {
        this.deadlineName = deadlineName;
    }

    public String getDeadlineCode() {
        return deadlineCode;
    }

    public void setDeadlineCode(String deadlineCode) {
        this.deadlineCode = deadlineCode;
    }
}