package com.cuc.gin.model;

import java.io.Serializable;

/**
 * @author : Chen X.T.
 * @since : 2020/2/28, 周五
 **/
public class TestResultEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private String result;
    private Integer value;
    private Long createTime;

    public TestResultEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
