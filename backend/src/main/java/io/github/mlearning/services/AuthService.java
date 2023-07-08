package io.github.mlearning.services;

import io.github.mlearning.dtos.ValidationValue;
import io.github.mlearning.dtos.impl.LoginDto;
import io.github.mlearning.dtos.impl.RegisterDto;
import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.objects.Biography;
import io.github.mlearning.objects.ResponseMessage;
import io.github.mlearning.repositories.impl.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static io.github.mlearning.objects.Constants.*;

@Service
public class AuthService {

    private final ResponseEntity<?> invalidCredentials;
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService userService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public AuthService(ResponseEntity<?> invalidCredentials) {
        this.invalidCredentials = invalidCredentials;
    }

    @Autowired
    public AuthService() {
        this(ResponseMessage.INVALID_CREDENTIALS.buildResponse());
    }

    public ResponseEntity<?> login(LoginDto dto) {
        final ValidationValue validation = dto.getValidation();

        if (!dto.getValidation().isValid())
            return validation.buildFailureResponse();

        final Optional<UserEntity> result = repository.findByName(dto.getUsername());

        if (result.isEmpty())
            return this.invalidCredentials;

        final UserEntity user = result.get();

        if (!user.getPassword().equals(dto.getPassword()))
            return this.invalidCredentials;

        final String token = this.generateToken(user);

        return ResponseMessage.SUCCESS.buildResponse(
                Map.of("token", token)
        );
    }

    public ResponseEntity<?> register(RegisterDto dto) {
        final ValidationValue validation = dto.getValidation();

        if (!dto.getValidation().isValid())
            return validation.buildFailureResponse();

        final Optional<UserEntity> result = repository.findByName(dto.getUsername());

        if (result.isPresent())
            return ResponseMessage.USER_ALREADY_EXISTS.buildResponse();

        final UserEntity user = dto.toEntity();
        user.save(this.repository);

        final String token = this.generateToken(user);

        return ResponseMessage.SUCCESS.buildResponse(
                Map.of("token", token)
        );
    }

    public Optional<UserEntity> getUserFromToken(String token) {
        try {
            if (!token.startsWith(AUTHORIZATION_HEADER))
                return Optional.empty();

            final String formattedToken = token.substring(BEARER_SPACED.length());
            final Key key = Keys.hmacShaKeyFor(this.jwtSecret.getBytes());
            final Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(formattedToken);
            final Date now = new Date();

            String username = claims.getBody().getSubject();

            if (claims.getBody().getExpiration().before(now))
                return Optional.empty();

            return this.repository.findByName(username);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    public String generateToken(UserEntity user) {
        final Key key = Keys.hmacShaKeyFor(this.jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtExpiration))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

}
