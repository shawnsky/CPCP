package com.cuc.gin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Chen X.T.
 * @since : 2020/2/26, 周三
 **/
public class BoardEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String content;
    private Long createTime;

    public BoardEntity() {
    }

    public BoardEntity(String content, Long createTime) {
        this.content = content;
        this.createTime = createTime;
    }

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
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
