package com.cuc.gin.model;

import java.io.Serializable;

/**
 * @author : Chen X.T.
 * @since : 2020/2/27, 周四
 **/
public class TestEntryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String question;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;

    public TestEntryEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsA() {
        return ansA;
    }

    public void setAnsA(String ansA) {
        this.ansA = ansA;
    }

    public String getAnsB() {
        return ansB;
    }

    public void setAnsB(String ansB) {
        this.ansB = ansB;
    }

    public String getAnsC() {
        return ansC;
    }

    public void setAnsC(String ansC) {
        this.ansC = ansC;
    }

    public String getAnsD() {
        return ansD;
    }

    public void setAnsD(String ansD) {
        this.ansD = ansD;
    }
}
