package io.github.mlearning.dtos;

import io.github.mlearning.objects.ResponseMessage;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@Builder
public class ValidationValue {

    private Validation validation;
    private boolean valid;
    private String message;

    public ResponseEntity<?> buildFailureResponse() {
        return ResponseMessage.VALIDATION_FAILURE.buildResponse(
                Map.of("message", this.validation.getMessage())
        );
//        return ResponseEntity.badRequest().body(this.validation.getMessage());
    }

    public static ValidationValue buildFrom(Validation validation) {
        return ValidationValue.builder()
                .valid(false)
                .validation(validation)
                .message(validation.getMessage())
                .build();
    }

    public static ValidationValue success() {
        return ValidationValue.builder()
                .valid(true)
                .message("Validation success")
                .build();
    }

}
