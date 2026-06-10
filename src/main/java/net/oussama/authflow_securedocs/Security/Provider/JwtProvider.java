package net.oussama.authflow_securedocs.Security.Provider;

import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Security.AuthentificaitonObject.JwtAuthentifcaton;
import net.oussama.authflow_securedocs.Services.JwtServices;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtProvider implements AuthenticationProvider {
    private JwtServices  jwtServices;
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
     String token = authentication.getCredentials().toString();
     Boolean validate = jwtServices.validateToken(token);
     if(validate){
         String username = jwtServices.DecodeToken(token);
         return new JwtAuthentifcaton(username);
     }
     throw  new BadCredentialsException("Invalid token");
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentifcaton.class.isAssignableFrom(authentication);
    }
}
