package com.cuc.gin.model;

import java.io.Serializable;

/**
 * @author : Chen X.T.
 * @since : 2020/2/26, 周三
 **/
public class PostEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Long createTime;
    private Long userId;

    public PostEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
