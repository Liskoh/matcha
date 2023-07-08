package io.github.mlearning.services;

import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.objects.BornData;
import io.github.mlearning.repositories.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameAvailable(String username) {
        final Optional<UserEntity> result = userRepository.findByName(username);

        return result.isEmpty();
    }
}
