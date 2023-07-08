package io.github.mlearning.repositories.impl;

import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.repositories.MongoRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends MongoRepository<UserEntity> {

    public Optional<UserEntity> findByName(String name) {
        final Query query = new Query(Criteria.where("username").is(name));
        return this.findOne(query, UserEntity.class);
    }

}
