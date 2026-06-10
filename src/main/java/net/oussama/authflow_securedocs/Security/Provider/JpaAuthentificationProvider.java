package net.oussama.authflow_securedocs.Security.Provider;

import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Security.UserDetailsServices.UserDetailsServicesDataBase;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaAuthentificationProvider implements AuthenticationProvider {
    private final UserDetailsServicesDataBase userDetailsimpl;
    private final PasswordEncoder passwordEncoder;
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        System.out.println("username = " + username+" password = "+password);
        UserDetails userDetails = userDetailsimpl.loadUserByUsername(username);
        if (userDetails != null) {
            if(passwordEncoder.matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
