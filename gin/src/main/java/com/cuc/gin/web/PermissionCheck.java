package com.cuc.gin.web;

import com.cuc.gin.mapper.UserMapper;
import com.cuc.gin.model.UserEntity;
import com.cuc.gin.util.HTTPMessage;
import com.cuc.gin.util.HTTPMessageCode;
import com.cuc.gin.util.HTTPMessageText;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author : Chen X.T.
 * @since : 2020/3/9, 周一
 **/
@Component
@Aspect
public class PermissionCheck {

    private final ObjectMapper objectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public PermissionCheck(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("@annotation(com.cuc.gin.annotation.AdminRequired)")
    public void invoke(){}

    // After AuthInterceptor, no need to check token
    @Around("invoke()")
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];
        String userId = (String) request.getAttribute("userId");
        UserEntity user = userMapper.getOne(Long.parseLong(userId));
        if (user == null || user.getStatus() != 999) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json;charset=UTF-8");
            try(PrintWriter writer = response.getWriter()){
                writer.print(objectMapper.writeValueAsString(new HTTPMessage<Void>(HTTPMessageCode.Common.FAILURE, HTTPMessageText.Common.FAILURE)));
            }
            return null;
        }
        return pjp.proceed(args);
    }
}
