package net.oussama.authflow_securedocs.Exception.SecurityExceptoin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.oussama.authflow_securedocs.Dto.ErrorResponeDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomEntrypoin implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("Custom Authentication EntryPoint");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        ErrorResponeDto errorResponeDto = ErrorResponeDto.builder()
                .message("Authentication Failed")
                .status(HttpStatus.valueOf(HttpServletResponse.SC_UNAUTHORIZED))
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        response.getWriter().print(new ObjectMapper().writeValueAsString(errorResponeDto));
      //  response.getWriter().write("{\"error\":\"Unauthorized\"}");
    }
}
