package io.github.mlearning.repositories;

import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMongoRepository<T> {
    T save(T entity);

    void delete(T entity);

    Optional<T> findOne(Query query, Class<T> entityClass);

    Optional<T> findOneById(UUID uuid, Class<T> entityClass);

    List<T> find(Query query, Class<T> entityClass);

    List<T> find(Query query, Class<T> entityClass, int amount);

    List<T> findAll(Class<T> entityClass);

    void clear(Class<T> entityClass);
}