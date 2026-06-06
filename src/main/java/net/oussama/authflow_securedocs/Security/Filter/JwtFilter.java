package net.oussama.authflow_securedocs.Security.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.oussama.authflow_securedocs.Services.JwtServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private JwtServices jwtServices;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String auth = request.getHeader("Authorization");
      String token =  null;
      String username = null;
      if(auth != null && auth.startsWith("Bearer ")) {
          token = auth.substring(7);
          username  = jwtServices.DecodeToken(token);
      }
      if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {

      }
    }
}
