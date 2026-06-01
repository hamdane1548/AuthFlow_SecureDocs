package net.oussama.authflow_securedocs.Services;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Dto.SuccessReponse;
import net.oussama.authflow_securedocs.Entity.User;
import net.oussama.authflow_securedocs.Exception.UserException;
import net.oussama.authflow_securedocs.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesUser {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public Boolean createUser(User user){
        Optional<User> usercheck = userRepository.findByUsername(user.getUsername());
        if(usercheck.isPresent()){
            throw  new UserException("User already exists with username " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public Authentication loginUser(String username, String password){
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(username,password)
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
      return authentication1;
    }
}
