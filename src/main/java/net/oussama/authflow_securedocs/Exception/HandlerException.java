package net.oussama.authflow_securedocs.Exception;

import net.oussama.authflow_securedocs.Dto.ErrorResponeDto;
import net.oussama.authflow_securedocs.Exception.SecurityExceptoin.CustomAccessDeniedHandler;
import net.oussama.authflow_securedocs.Exception.SecurityExceptoin.CustomEntrypoin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserException.class)
   public ResponseEntity<ErrorResponeDto> UserException(UserException userException, WebRequest request) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            ErrorResponeDto.builder()
                    .message(userException.getMessage())
                    .status(HttpStatus.UNAUTHORIZED)
                    .path(request.getDescription(false))
                    .timestamp(LocalDateTime.now())
                    .build()
    );
   }

}
