package net.oussama.authflow_securedocs.Security;

import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Exception.SecurityExceptoin.CustomAccessDeniedHandler;
import net.oussama.authflow_securedocs.Exception.SecurityExceptoin.CustomEntrypoin;
import net.oussama.authflow_securedocs.Security.Filter.HeaderResponse;
import net.oussama.authflow_securedocs.Security.Filter.JwtFilter;
import net.oussama.authflow_securedocs.Services.JwtServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityFilterChainconfig {
    private JwtServices jwtFilter;
    private AuthenticationConfiguration authenticationConfiguration;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     http.authorizeHttpRequests(request->{
         request.requestMatchers("/register","/login").permitAll();
         request.requestMatchers("/test").authenticated();
     });
     http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

      http.addFilterAt(new HeaderResponse(), UsernamePasswordAuthenticationFilter.class);
      http.addFilterBefore(new JwtFilter(this.authenticationManager(authenticationConfiguration),jwtFilter), UsernamePasswordAuthenticationFilter.class);
      http.cors(customize -> {
          CorsConfigurationSource config = request -> {
              CorsConfiguration cors = new CorsConfiguration();
              cors.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "OPTIONS"));
              cors.setAllowedHeaders(List.of("Authorization"));
              return cors;
          };
          customize.configurationSource(config);
      });
     http.csrf(httpSecurityCsrfConfigurer ->  httpSecurityCsrfConfigurer.disable());
    return  http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

}
