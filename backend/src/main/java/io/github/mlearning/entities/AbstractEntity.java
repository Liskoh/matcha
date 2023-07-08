package io.github.mlearning.entities;

import io.github.mlearning.repositories.MongoRepository;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEntity<T> implements Serializable {
    protected UUID id;

    public AbstractEntity() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public abstract AbstractEntity<T> save(MongoRepository<T> repository);

    public abstract void delete(MongoRepository<T> repository);

}
