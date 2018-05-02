package com.restaurant_vote.web.security;

import com.restaurant_vote.model.ExceptionInfo;
import com.restaurant_vote.web.json.JsonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component("accessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle
            (HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ExceptionInfo exceptionInfo=new ExceptionInfo();
        exceptionInfo.setUrl(request.getRequestURL().toString());
        exceptionInfo.setMessage(ex.getMessage());
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.writeValue(exceptionInfo));
    }
}
