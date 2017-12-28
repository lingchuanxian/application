package com.project.application.bean;

import java.util.Date;

import javax.persistence.*;

@Table(name = "ap_project")
public class Project {
	
	public static final int STATE_UNSIGN = 0;
	public static final int STATE_SIGNING = 1;
	public static final int STATE_SIGNEND = 2;
	public static final int STATE_SELECTEDGROUP = 3;
	public static final int STATE_CONSTRUCTION = 4;
	public static final int STATE_FORCHECK = 5;
	public static final int STATE_CHECKING = 6;
	public static final int STATE_CHECKPASS = 7;
	public static final int STATE_CHECKUNPASS = 8;
	public static final int STATE_REPAIRING = 9;
	public static final int STATE_FINISHED = 10;
	
    @Id
    @Column(name = "pr_id")
    @GeneratedValue(generator = "JDBC")
    private Integer prId;

    @Column(name = "pr_name")
    private String prName;

    @Column(name = "pr_type")
    private Integer prType;

    /**
     * 0 报名未开始。
     * 1 报名阶段。
     * 2 报名结束，待分配承包商
     * 3 已分配承包商，待开工。
     * 4 正在建设
     * 5 完工，提交验收报告，待验收
     * 6 验收中
     * 7 验收通过，项目完成。
     * 8 验收未通过，待返修
     * 9 返修中
     * 10 完成
     */
    @Column(name = "pr_state")
    private Integer prState;
    
    @Column(name = "pr_budget")
    private String prBudget;
    
    @Column(name = "pr_expect_date")
    private String prExpectDate;

    @Column(name = "pr_contacts")
    private String prContacts;

    @Column(name = "pr_contacts_phone")
    private String prContactsPhone;

    @Column(name = "pr_description")
    private String prDescription;
    
    @Column(name = "pr_adddate")
    private Date prAdddate;
    
    @Column(name = "pr_sign_start_date")
    private Date prSignStartDate;
    
    @Column(name = "pr_sign_end_date")
    private Date prSignEndDate;
    
    @Transient
    private ProjectType projectType;
    
    
    public Date getPrSignStartDate() {
		return prSignStartDate;
	}

	public void setPrSignStartDate(Date prSignStartDate) {
		this.prSignStartDate = prSignStartDate;
	}

	public Date getPrSignEndDate() {
		return prSignEndDate;
	}

	public void setPrSignEndDate(Date prSignEndDate) {
		this.prSignEndDate = prSignEndDate;
	}

	public String getPrBudget() {
		return prBudget;
	}

	public void setPrBudget(String prBudget) {
		this.prBudget = prBudget;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public Date getPrAdddate() {
		return prAdddate;
	}

	public void setPrAdddate(Date prAdddate) {
		this.prAdddate = prAdddate;
	}

	/**
     * @return pr_id
     */
    public Integer getPrId() {
        return prId;
    }

    /**
     * @param prId
     */
    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    /**
     * @return pr_name
     */
    public String getPrName() {
        return prName;
    }

    /**
     * @param prName
     */
    public void setPrName(String prName) {
        this.prName = prName;
    }

    /**
     * @return pr_type
     */
    public Integer getPrType() {
        return prType;
    }

    /**
     * @param prType
     */
    public void setPrType(Integer prType) {
        this.prType = prType;
    }

    /**
     * @return pr_state
     */
    public Integer getPrState() {
        return prState;
    }

    /**
     * @param prState
     */
    public void setPrState(Integer prState) {
        this.prState = prState;
    }

    /**
     * @return pr_expect_date
     */
    public String getPrExpectDate() {
        return prExpectDate;
    }

    /**
     * @param prExpectDate
     */
    public void setPrExpectDate(String prExpectDate) {
        this.prExpectDate = prExpectDate;
    }

    /**
     * @return pr_contacts
     */
    public String getPrContacts() {
        return prContacts;
    }

    /**
     * @param prContacts
     */
    public void setPrContacts(String prContacts) {
        this.prContacts = prContacts;
    }

    /**
     * @return pr_contacts_phone
     */
    public String getPrContactsPhone() {
        return prContactsPhone;
    }

    /**
     * @param prContactsPhone
     */
    public void setPrContactsPhone(String prContactsPhone) {
        this.prContactsPhone = prContactsPhone;
    }

    /**
     * @return pr_description
     */
    public String getPrDescription() {
        return prDescription;
    }

    /**
     * @param prDescription
     */
    public void setPrDescription(String prDescription) {
        this.prDescription = prDescription;
    }

	@Override
	public String toString() {
		return "Project [prId=" + prId + ", prName=" + prName + ", prType=" + prType + ", prState=" + prState
				+ ", prBudget=" + prBudget + ", prExpectDate=" + prExpectDate + ", prContacts=" + prContacts
				+ ", prContactsPhone=" + prContactsPhone + ", prDescription=" + prDescription + ", prAdddate="
				+ prAdddate + ", prSignStartDate=" + prSignStartDate + ", prSignEndDate=" + prSignEndDate
				+ ", projectType=" + projectType + "]";
	}
}