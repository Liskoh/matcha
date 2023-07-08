package io.github.mlearning.services;

import io.github.mlearning.entities.impl.ConfirmationKeyEntity;
import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.enums.RegistrationStatus;
import io.github.mlearning.objects.ResponseMessage;
import io.github.mlearning.repositories.impl.ConfirmationKeyRepository;
import io.github.mlearning.repositories.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailService {

    @Autowired
    private ConfirmationKeyRepository confirmationKeyRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> confirmRegistration(UserEntity user, String keyCode) {
        final Optional<ConfirmationKeyEntity> result = this.confirmationKeyRepository.findByUsername(user.getUsername());

        if (result.isEmpty())
            return ResponseMessage.KEY_NOT_FOUND.buildResponse();

        final ConfirmationKeyEntity key = result.get();

        if (!key.getId().toString().equals(keyCode))
            return ResponseMessage.KEY_NOT_FOUND.buildResponse();

        if (key.isExpired())
            return ResponseMessage.KEY_NOT_FOUND.buildResponse();

        key.delete(this.confirmationKeyRepository);

        user.setRegistrationStatus(RegistrationStatus.REGISTERED);
        user.save(this.userRepository);

        return ResponseMessage.SUCCESS.buildResponse();
    }

    public ResponseEntity<?> generateNewKey(UserEntity user) {
        if (user.getRegistrationStatus() == RegistrationStatus.REGISTERED)
            return ResponseMessage.USER_ALREADY_CONFIRMED.buildResponse();

        final ConfirmationKeyEntity key = this.createOrUpdateKey(user);

        //TODO: send email
        return ResponseMessage.KEY_SENT_SUCCESS.buildResponse();
    }

    public ConfirmationKeyEntity createKey(UserEntity user) {
        final ConfirmationKeyEntity key = new ConfirmationKeyEntity(user.getUsername());
        key.save(this.confirmationKeyRepository);

        return key;
    }

    public ConfirmationKeyEntity createOrUpdateKey(UserEntity user) {
        final Optional<ConfirmationKeyEntity> result = this.confirmationKeyRepository.findByUsername(user.getUsername());

        if (result.isEmpty())
            return this.createKey(user);

        final ConfirmationKeyEntity key = result.get();

        if (key.isExpired())
            key.delete(this.confirmationKeyRepository);

        return this.createKey(user);
    }
}
