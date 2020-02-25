package com.cuc.gin.web;

import com.cuc.gin.mapper.UserMapper;
import com.cuc.gin.model.UserEntity;
import com.cuc.gin.util.*;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * @author : Chen X.T.
 * @since : 2020/1/26, 周日
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HTTPMessage<Void> login(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            throw new IllegalArgumentException();
        }

        UserEntity userEntity = userMapper.getOneByUsername(username);
        if (userEntity != null && userEntity.getPassword().equals(CryptoUtil.getHashedPassword(password))) {
            return new HTTPMessage<>(
                    HTTPMessageCode.Login.OK,
                    HTTPMessageText.Login.OK
            );
        } else {
            return new HTTPMessage<>(
                    HTTPMessageCode.Login.FAILURE,
                    HTTPMessageText.Login.FAILURE
            );
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HTTPMessage<UserEntity> register(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        if (Strings.isNullOrEmpty(username)
                || Strings.isNullOrEmpty(password)) {
            throw new IllegalArgumentException();
        }
        UserEntity userEntity = userMapper.getOneByUsername(username);
        if (userEntity != null) {
            return new HTTPMessage<>(
                    HTTPMessageCode.Register.EXISTS,
                    HTTPMessageText.Register.EXISTS
            );
        }

        UserEntity user = new UserEntity(username,CryptoUtil.getHashedPassword(password));
        userMapper.add(user);
        return new HTTPMessage<>(
                HTTPMessageCode.Register.OK,
                HTTPMessageText.Register.OK,
                user
        );
    }


}
