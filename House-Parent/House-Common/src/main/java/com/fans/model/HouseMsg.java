package com.fans.model;

import java.util.Date;

public class HouseMsg {
    /**
     * 
     */
    private Long id;

    /**
     * 消息
     */
    private String msg;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 经纪人id
     */
    private Long agentId;

    /**
     * 房屋id
     */
    private Long houseId;

    /**
     * 用户姓名
     */
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}