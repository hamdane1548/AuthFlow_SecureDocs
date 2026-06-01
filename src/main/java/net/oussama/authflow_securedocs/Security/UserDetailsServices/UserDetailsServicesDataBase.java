package net.oussama.authflow_securedocs.Security.UserDetailsServices;

import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Entity.User;
import net.oussama.authflow_securedocs.Repository.UserRepository;
import net.oussama.authflow_securedocs.Security.UserDetailsImp.UserDetailsimpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServicesDataBase implements UserDetailsService {
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User userDetails = User.builder()
                    .email(user.get().getEmail())
                    .Role(user.get().getRole())
                    .Password(user.get().getPassword())
                    .build();
            return new UserDetailsimpl(userDetails);
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
