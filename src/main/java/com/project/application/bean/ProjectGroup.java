package com.project.application.bean;

import javax.persistence.*;

@Table(name = "ap_project_group")
public class ProjectGroup {
    @Id
    @Column(name = "pg_id")
    private Integer pgId;

    @Column(name = "pg_name")
    private String pgName;

    @Column(name = "pg_address")
    private String pgAddress;

    @Column(name = "pg_scale")
    private String pgScale;

    @Column(name = "pg_leader")
    private String pgLeader;

    @Column(name = "pg_leader_phone")
    private String pgLeaderPhone;
    
    @Column(name = "pg_leader_pwd")
    private String pgLeaderPwd;

    @Column(name = "pg_leader_email")
    private String pgLeaderEmail;

    @Column(name = "pg_isdel")
    private Integer pgIsdel;

    @Column(name = "pg_brief")
    private String pgBrief;

    
    public String getPgLeaderPwd() {
		return pgLeaderPwd;
	}

	public void setPgLeaderPwd(String pgLeaderPwd) {
		this.pgLeaderPwd = pgLeaderPwd;
	}

	/**
     * @return pg_id
     */
    public Integer getPgId() {
        return pgId;
    }

    /**
     * @param pgId
     */
    public void setPgId(Integer pgId) {
        this.pgId = pgId;
    }

    /**
     * @return pg_name
     */
    public String getPgName() {
        return pgName;
    }

    /**
     * @param pgName
     */
    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    /**
     * @return pg_address
     */
    public String getPgAddress() {
        return pgAddress;
    }

    /**
     * @param pgAddress
     */
    public void setPgAddress(String pgAddress) {
        this.pgAddress = pgAddress;
    }

    /**
     * @return pg_scale
     */
    public String getPgScale() {
        return pgScale;
    }

    /**
     * @param pgScale
     */
    public void setPgScale(String pgScale) {
        this.pgScale = pgScale;
    }

    /**
     * @return pg_leader
     */
    public String getPgLeader() {
        return pgLeader;
    }

    /**
     * @param pgLeader
     */
    public void setPgLeader(String pgLeader) {
        this.pgLeader = pgLeader;
    }

    /**
     * @return pg_leader_phone
     */
    public String getPgLeaderPhone() {
        return pgLeaderPhone;
    }

    /**
     * @param pgLeaderPhone
     */
    public void setPgLeaderPhone(String pgLeaderPhone) {
        this.pgLeaderPhone = pgLeaderPhone;
    }

    /**
     * @return pg_leader_email
     */
    public String getPgLeaderEmail() {
        return pgLeaderEmail;
    }

    /**
     * @param pgLeaderEmail
     */
    public void setPgLeaderEmail(String pgLeaderEmail) {
        this.pgLeaderEmail = pgLeaderEmail;
    }

    /**
     * @return pg_isdel
     */
    public Integer getPgIsdel() {
        return pgIsdel;
    }

    /**
     * @param pgIsdel
     */
    public void setPgIsdel(Integer pgIsdel) {
        this.pgIsdel = pgIsdel;
    }

    /**
     * @return pg_brief
     */
    public String getPgBrief() {
        return pgBrief;
    }

    /**
     * @param pgBrief
     */
    public void setPgBrief(String pgBrief) {
        this.pgBrief = pgBrief;
    }

	@Override
	public String toString() {
		return "ProjectGroup [pgId=" + pgId + ", pgName=" + pgName + ", pgAddress=" + pgAddress + ", pgScale=" + pgScale
				+ ", pgLeader=" + pgLeader + ", pgLeaderPhone=" + pgLeaderPhone + ", pgLeaderPwd=" + pgLeaderPwd
				+ ", pgLeaderEmail=" + pgLeaderEmail + ", pgIsdel=" + pgIsdel + ", pgBrief=" + pgBrief + "]";
	}
}