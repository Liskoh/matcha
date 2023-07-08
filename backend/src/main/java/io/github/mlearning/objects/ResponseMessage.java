package io.github.mlearning.objects;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ResponseMessage {
    //`200
    SUCCESS(HttpStatus.OK, "Success."),
    KEY_SENT_SUCCESS(HttpStatus.OK, "Please check your email for confirmation key."),
    // 401
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Login or password is incorrect."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token."),
    USER_ALREADY_EXISTS(HttpStatus.UNAUTHORIZED, "User already exists."),
    // 403
    USER_ALREADY_CONFIRMED(HttpStatus.FORBIDDEN, "User already confirmed."),
    // BAD_REQUEST
    VALIDATION_FAILURE(HttpStatus.BAD_REQUEST, null),

    KEY_NOT_FOUND(HttpStatus.NOT_FOUND, "Key not found, check your email or ask for a new one."),
    ;

    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public ResponseEntity<?> buildResponse() {
        return this.buildResponse(null);
    }

    public ResponseEntity<?> buildResponse(Map<String, Object> body) {
        if (this.message != null) {
            final Map<String, Object> response = new HashMap<>(Map.of(
                    "message", this.message
            ));

            if (body != null)
                response.putAll(body);

            return ResponseEntity.status(this.status).body(response);
        }

        return ResponseEntity.status(this.status).body(body);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
