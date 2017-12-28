package com.project.application.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ap_admin")
public class Admin {
    @Id
    @Column(name = "ad_id")
    private Integer adId;

    @Column(name = "ad_loginname")
    private String adLoginname;

    @Column(name = "ad_pwd")
    private String adPwd;

    @Column(name = "ad_name")
    private String adName;

    /**
     * 0：超级管理员
1：普通管理员
     */
    @Column(name = "ad_type")
    private Integer adType;

    @Column(name = "ad_state")
    private Integer adState;

    @Column(name = "ad_lastlogindate")
    private Date adLastlogindate;

    @Column(name = "ad_lastloginip")
    private String adLastloginip;

    @Column(name = "ad_adddate")
    private Date adAdddate;

    /**
     * @return ad_id
     */
    public Integer getAdId() {
        return adId;
    }

    /**
     * @param adId
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    /**
     * @return ad_loginname
     */
    public String getAdLoginname() {
        return adLoginname;
    }

    /**
     * @param adLoginname
     */
    public void setAdLoginname(String adLoginname) {
        this.adLoginname = adLoginname;
    }

    /**
     * @return ad_pwd
     */
    public String getAdPwd() {
        return adPwd;
    }

    /**
     * @param adPwd
     */
    public void setAdPwd(String adPwd) {
        this.adPwd = adPwd;
    }

    /**
     * @return ad_name
     */
    public String getAdName() {
        return adName;
    }

    /**
     * @param adName
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 获取0：超级管理员
1：普通管理员
     *
     * @return ad_type - 0：超级管理员
1：普通管理员
     */
    public Integer getAdType() {
        return adType;
    }

    /**
     * 设置0：超级管理员
1：普通管理员
     *
     * @param adType 0：超级管理员
1：普通管理员
     */
    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    /**
     * @return ad_state
     */
    public Integer getAdState() {
        return adState;
    }

    /**
     * @param adState
     */
    public void setAdState(Integer adState) {
        this.adState = adState;
    }

    /**
     * @return ad_lastlogindate
     */
    public Date getAdLastlogindate() {
        return adLastlogindate;
    }

    /**
     * @param adLastlogindate
     */
    public void setAdLastlogindate(Date adLastlogindate) {
        this.adLastlogindate = adLastlogindate;
    }

    /**
     * @return ad_lastloginip
     */
    public String getAdLastloginip() {
        return adLastloginip;
    }

    /**
     * @param adLastloginip
     */
    public void setAdLastloginip(String adLastloginip) {
        this.adLastloginip = adLastloginip;
    }

    /**
     * @return ad_adddate
     */
    public Date getAdAdddate() {
        return adAdddate;
    }

    /**
     * @param adAdddate
     */
    public void setAdAdddate(Date adAdddate) {
        this.adAdddate = adAdddate;
    }
}