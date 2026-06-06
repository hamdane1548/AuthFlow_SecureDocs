package net.oussama.authflow_securedocs.Security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Services.JwtServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
@AllArgsConstructor
public class SuccessHandlerImplem implements AuthenticationSuccessHandler {
    private final JwtServices jwtServices;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       String jwt = jwtServices.generateToken(authentication);

    }
}
