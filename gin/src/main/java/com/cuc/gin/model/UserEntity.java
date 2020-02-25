package com.cuc.gin.model;

import com.cuc.gin.util.Gender;
import org.apache.catalina.User;

import java.io.Serializable;

/**
 * @author : Chen X.T.
 * @since : 2020/1/26, 周日
 **/
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
    private Gender gender;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserEntity() {}

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password, Gender gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
