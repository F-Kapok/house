package com.fans.model;

import java.util.Date;

public class Comment {
    /**
     *
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 房屋id
     */
    private Long houseId;

    /**
     * 发布时间戳
     */
    private Date createTime;

    /**
     * 博客id
     */
    private Integer blogId;

    /**
     * 类型1-房产评论，2-博客评论
     */
    private Boolean type;

    /**
     * 评论用户
     */
    private Long userId;

    private String userName;

    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}