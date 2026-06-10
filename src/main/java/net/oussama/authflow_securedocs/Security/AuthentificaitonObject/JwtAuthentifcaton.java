package net.oussama.authflow_securedocs.Security.AuthentificaitonObject;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentifcaton extends AbstractAuthenticationToken {
   private final String token;
   private final Object principal;
    public JwtAuthentifcaton(String token) {
        super((Collection<? extends GrantedAuthority>) null);
        this.token = token;
        this.principal = null;
        setAuthenticated(false);
    }
    public JwtAuthentifcaton(Object principal,String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.principal = principal;
        setAuthenticated(true);
    }
    @Override
    public @Nullable Object getCredentials() {
        return token;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return principal;
    }
}
