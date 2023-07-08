package io.github.mlearning.entities.impl;

import io.github.mlearning.entities.AbstractEntity;
import io.github.mlearning.enums.GenderType;
import io.github.mlearning.enums.RegistrationStatus;
import io.github.mlearning.enums.SexualPreference;
import io.github.mlearning.objects.Biography;
import io.github.mlearning.repositories.MongoRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@Document(collection = "users")
public class UserEntity extends AbstractEntity<UserEntity> {


    private String username, lastName, firstName, email, biography, password;

    private GenderType genderType;
    private SexualPreference sexualPreference;
    private RegistrationStatus registrationStatus;

    private int popularity;

    private List<String> tags;
    private List<String> pictures;

    public UserEntity() {
    super();
}

    public UserEntity(String username, String firstName, String lastName, String password, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.registrationStatus = RegistrationStatus.UNREGISTERED;
        this.pictures = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.popularity = 0;
    }

    @Override
    public UserEntity save(MongoRepository<UserEntity> repository) {
        return repository.save(this);
    }

    @Override
    public void delete(MongoRepository<UserEntity> repository) {
        repository.delete(this);
    }
}
