package com.cuc.gin.web;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;

/**
 * @author : Chen X.T.
 * @since : 2020/2/26, 周三
 **/
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private Key jwtKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        // Accept if login or register request
        if (uri.matches("/auth/[a-zA-Z]*")) {
            return true;
        }
        String compactJws = request.getHeader("Authorization");
        if (Strings.isNullOrEmpty(compactJws)) {
            System.out.println("Null Token");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(compactJws);
            // OK, we can trust this JWT. Then check expire date.
            if (jws.getBody().getExpiration().before(new Date())) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
            // Need to check Subject in Controller.
            return true;
        } catch (JwtException e) {
            //don't trust the JWT!
            System.out.println("Check token failed");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
