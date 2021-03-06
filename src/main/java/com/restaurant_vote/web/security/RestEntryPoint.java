package com.restaurant_vote.web.security;

import com.restaurant_vote.model.ExceptionInfo;
import com.restaurant_vote.web.json.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("entryPoint")
public class RestEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence
            (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm="+ getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ExceptionInfo exceptionInfo=new ExceptionInfo();
        exceptionInfo.setUrl(request.getRequestURL().toString());
        exceptionInfo.setMessage(authEx.getMessage());
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.writeValue(exceptionInfo));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Restaurant vote");
        super.afterPropertiesSet();
    }

}
