package io.github.mlearning.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MongoRepository<T> implements IMongoRepository<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public T save(T entity) {
        return this.mongoTemplate.save(entity);
    }

    @Override
    public void delete(T entity) {
        this.mongoTemplate.remove(entity);
    }

    @Override
    public Optional<T> findOne(Query query, Class<T> entityClass) {
        return Optional.ofNullable(this.mongoTemplate.findOne(query, entityClass));
    }

    @Override
    public Optional<T> findOneById(UUID uuid, Class<T> entityClass) {
        final Query query = new Query(Criteria.where("_id").is(uuid));

        return this.findOne(query, entityClass);
    }

    @Override
    public List<T> find(Query query, Class<T> entityClass) {
        return this.mongoTemplate.find(query, entityClass);
    }

    @Override
    public List<T> find(Query query, Class<T> entityClass, int amount) {
        return this.mongoTemplate.find(query.limit(amount), entityClass);
    }

    @Override
    public List<T> findAll(Class<T> entityClass) {
        return this.mongoTemplate.findAll(entityClass);
    }

    @Override
    public void clear(Class<T> entityClass) {
        this.mongoTemplate.dropCollection(entityClass);
    }

}
