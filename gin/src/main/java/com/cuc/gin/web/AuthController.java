package com.cuc.gin.web;

import com.cuc.gin.mapper.UserMapper;
import com.cuc.gin.model.UserEntity;
import com.cuc.gin.util.*;
import com.cuc.gin.vo.UserInfoVo;
import com.google.common.base.Strings;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    private Key jwtKey;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HTTPMessage<UserInfoVo> login(@RequestBody Map map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            throw new IllegalArgumentException();
        }

        UserEntity userEntity = userMapper.getOneByUsername(username);
        if (userEntity != null && userEntity.getPassword().equals(CryptoUtil.getHashedPassword(password))) {
            // Sign JWT token
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR_OF_DAY, 1);
            String jws = Jwts.builder().setSubject(userEntity.getId().toString()).setExpiration(cal.getTime()).signWith(jwtKey).compact();
            UserInfoVo userInfo = new UserInfoVo(userEntity.getId(), userEntity.getUsername(), jws);
            return new HTTPMessage<>(
                    HTTPMessageCode.Login.OK,
                    HTTPMessageText.Login.OK,
                    userInfo
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
