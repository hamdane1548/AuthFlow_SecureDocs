package net.oussama.authflow_securedocs.Security.Filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Security.AuthentificaitonObject.JwtAuthentifcaton;
import net.oussama.authflow_securedocs.Services.JwtServices;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private  AuthenticationManager authenticationManager;
    private JwtServices jwtServices;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String auth = request.getHeader("Authorization");
      String token =  null;
      String username = null;
      if(auth != null && auth.startsWith("Bearer ")) {
          token = auth.substring(7);
          System.out.println("fwfqwfwq");
          try {
              username  = jwtServices.DecodeToken(token);
          }catch (JwtException e){
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.setHeader("WWW-Authenticate","Bearer");
             throw new JwtException("tkone not valide");
          }
      }
      else {
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.setHeader("WWW-Authenticate", "Bearer Bearer");
      }
      if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
          Authentication authentication = new JwtAuthentifcaton(token);
          Boolean check_token = jwtServices.validateToken(token);
          if(check_token) {
              List<String> role = List.of("MANAGER");
              List<SimpleGrantedAuthority> roles= role.stream().map(role_va->{
                  return new SimpleGrantedAuthority(role_va);
              }).toList();
              Authentication authentication1 = new JwtAuthentifcaton((Object) username,token, roles);
              System.out.println(authentication1);
              SecurityContextHolder.getContext().setAuthentication(authentication1);
          }
      }
      filterChain.doFilter(request,response);
    }


}
