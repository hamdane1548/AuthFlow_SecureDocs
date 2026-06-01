package net.oussama.authflow_securedocs.Exception.SecurityExceptoin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
       response.setContentType("text/html;charset=UTF-8");
       response.setStatus(HttpServletResponse.SC_FORBIDDEN);
       response.getWriter().println("Access Denied");
        System.out.println("Access Denied");
        response.getWriter().write("{\"error\": \"Unauthorized - invalid or missing token\"}");
    }
}
