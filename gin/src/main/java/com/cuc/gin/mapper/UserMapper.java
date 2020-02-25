package com.cuc.gin.mapper;

import com.cuc.gin.model.UserEntity;

/**
 * @author : Chen X.T.
 * @since : 2020/1/26, 周日
 **/
public interface UserMapper {

    UserEntity getOne(Long id);

    UserEntity getOneByUsername(String username);

    String getPasswordByUsername(String username);

    void add(UserEntity user);

}
