package com.hobart.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * session被踢掉的过期 
 */
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("msessage","您的账号已经在另外一个地方被登录，您将被迫下线");
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(result));
    }
}
