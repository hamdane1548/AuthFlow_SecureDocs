package net.oussama.authflow_securedocs.Dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class SuccessReponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
