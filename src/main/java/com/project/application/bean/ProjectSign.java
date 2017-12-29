package com.project.application.bean;

import javax.persistence.*;

@Table(name = "ap_project_sign")
public class ProjectSign {
    @Id
    @Column(name = "ps_id")
    private Integer psId;

    @Column(name = "ps_pid")
    private Integer psPid;

    @Column(name = "ps_gid")
    private Integer psGid;

    @Transient
    private Project project;
    
    @Transient
    private ProjectGroup projectGroup;
    
    
    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ProjectGroup getProjectGroup() {
		return projectGroup;
	}

	public void setProjectGroup(ProjectGroup projectGroup) {
		this.projectGroup = projectGroup;
	}

	/**
     * @return ps_id
     */
    public Integer getPsId() {
        return psId;
    }

    /**
     * @param psId
     */
    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    /**
     * @return ps_pid
     */
    public Integer getPsPid() {
        return psPid;
    }

    /**
     * @param psPid
     */
    public void setPsPid(Integer psPid) {
        this.psPid = psPid;
    }

    /**
     * @return ps_gid
     */
    public Integer getPsGid() {
        return psGid;
    }

    /**
     * @param psGid
     */
    public void setPsGid(Integer psGid) {
        this.psGid = psGid;
    }
}