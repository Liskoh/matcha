package io.github.mlearning.controllers;

import io.github.mlearning.dtos.impl.LoginDto;
import io.github.mlearning.dtos.impl.RegisterDto;
import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.objects.ResponseMessage;
import io.github.mlearning.services.AuthService;
import io.github.mlearning.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private MailService mailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        return this.authService.login(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        return this.authService.register(dto);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<?> confirmMail(@RequestHeader("Authorization") String token, @RequestParam("key") String key) {
        final Optional<UserEntity> result = this.authService.getUserFromToken(token);

        if (result.isEmpty())
            return ResponseMessage.INVALID_TOKEN.buildResponse();

        return this.mailService.confirmRegistration(result.get(), key);
    }

    @GetMapping("/new-key")
    public ResponseEntity<?> resendConfirmation(@RequestHeader("Authorization") String token) {
        final Optional<UserEntity> result = this.authService.getUserFromToken(token);

        if (result.isEmpty())
            return ResponseMessage.INVALID_TOKEN.buildResponse();

        return this.mailService.generateNewKey(result.get());
    }

}
