package net.oussama.authflow_securedocs.RestControlleur;

import lombok.AllArgsConstructor;
import net.oussama.authflow_securedocs.Dto.LoginDto;
import net.oussama.authflow_securedocs.Dto.SuccessReponse;
import net.oussama.authflow_securedocs.Entity.User;
import net.oussama.authflow_securedocs.Services.ServicesUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class UserControlleur {
    private ServicesUser servicesUser;
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        Boolean usercreate = servicesUser.createUser(user);
        if (usercreate) {
            return  ResponseEntity.status(HttpStatus.CREATED).body(
                    SuccessReponse.builder()
                            .message("User registered successfully")
                            .status(HttpStatus.OK)
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    SuccessReponse.builder()
            );
        }
    };
    @PostMapping("/login")
    public  Authentication loginUser(@RequestBody LoginDto loginDto) {
        //System.out.println(loginDto);
        Authentication authentication= servicesUser.loginUser(loginDto.getUsername(),  loginDto.getPassword());
        return authentication;
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return  ResponseEntity.status(HttpStatus.OK).body("test");
    }
}
