package io.github.mlearning.repositories.impl;

import io.github.mlearning.entities.impl.ConfirmationKeyEntity;
import io.github.mlearning.repositories.MongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConfirmationKeyRepository extends MongoRepository<ConfirmationKeyEntity> {

    public Optional<ConfirmationKeyEntity> findByUsername(String username) {
        final Query query = new Query(Criteria.where("username").is(username));
        return this.findOne(query, ConfirmationKeyEntity.class);
    }
}
