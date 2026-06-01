package net.oussama.authflow_securedocs.Security;

import net.oussama.authflow_securedocs.Exception.SecurityExceptoin.CustomAccessDeniedHandler;
import net.oussama.authflow_securedocs.Exception.SecurityExceptoin.CustomEntrypoin;
import net.oussama.authflow_securedocs.Security.JpaProvider.JpaAuthentificationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;

@Configuration
public class SecurityFilterChainconfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http.authorizeHttpRequests(request->{
         request.requestMatchers("/register","/login").permitAll();
         request.requestMatchers("/test").authenticated();
     });
     http.exceptionHandling(exception->{
         exception.accessDeniedHandler(new CustomAccessDeniedHandler());
         exception.authenticationEntryPoint(new CustomEntrypoin());
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
