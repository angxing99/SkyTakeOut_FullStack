package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT Token Interceptor for Admin and User
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Validate JWT
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Check if the current intercepted request is a Controller method or another type of resource
        if (!(handler instanceof HandlerMethod)) {
            // If it's not a dynamic method (i.e., not a Controller method), allow the request to proceed
            return true;
        }


        //1. Get token from header
        String token = request.getHeader(jwtProperties.getAdminTokenName());


        //2. Validate the header
        try {
            log.info("jwt validate:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("Current Emp id：", empId);
            // store employee id into thread local
            BaseContext.setCurrentId(empId);
            return true;
        } catch (Exception ex) {
            // Fail, throw 401
            response.setStatus(401);
            return false;
        }
    }
}
