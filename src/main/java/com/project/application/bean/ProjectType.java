package com.project.application.bean;

import javax.persistence.*;

@Table(name = "ap_project_type")
public class ProjectType {
    @Id
    @Column(name = "pt_id")
    private Integer ptId;

    @Column(name = "pt_name")
    private String ptName;

    /**
     * @return pt_id
     */
    public Integer getPtId() {
        return ptId;
    }

    /**
     * @param ptId
     */
    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

    /**
     * @return pt_name
     */
    public String getPtName() {
        return ptName;
    }

    /**
     * @param ptName
     */
    public void setPtName(String ptName) {
        this.ptName = ptName;
    }
}