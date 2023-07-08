package io.github.mlearning;

import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.repositories.impl.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoAppTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void contextLoads() {
//        this.repository.clear(UserEntity.class);
//
//        List<UserEntity> users = this.repository.findAll(UserEntity.class);
//
//        assertTrue(users.isEmpty());
        System.out.println("Hello world!");
    }

}
