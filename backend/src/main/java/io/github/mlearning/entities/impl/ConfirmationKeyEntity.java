package io.github.mlearning.entities.impl;

import io.github.mlearning.entities.AbstractEntity;
import io.github.mlearning.repositories.MongoRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class ConfirmationKeyEntity extends AbstractEntity<ConfirmationKeyEntity> {

    public ConfirmationKeyEntity() {}

    public ConfirmationKeyEntity(String username) {
        this.username = username;
        this.expirationMillis = this.setCurrentExpiration();
    }

    private String username;
    private long expirationMillis;


    public long setCurrentExpiration() {
        return System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1L);
    }

   public boolean isExpired() {
        return System.currentTimeMillis() > this.expirationMillis;
    }

    @Override
    public AbstractEntity<ConfirmationKeyEntity> save(MongoRepository<ConfirmationKeyEntity> repository) {
        return repository.save(this);
    }

    @Override
    public void delete(MongoRepository<ConfirmationKeyEntity> repository) {
        repository.delete(this);
    }
}