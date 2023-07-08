package io.github.mlearning.dtos.impl;

import io.github.mlearning.dtos.IDto;
import io.github.mlearning.dtos.Validation;
import io.github.mlearning.dtos.ValidationValue;
import io.github.mlearning.entities.impl.UserEntity;
import io.github.mlearning.objects.BornData;
import lombok.Getter;

import java.util.Optional;

@Getter
public class RegisterDto implements IDto {

    private String username;
    private String firstName, lastName;
    private String password;
    private String email;
    private String bornDateString;
    private BornData bornData;

    public RegisterDto() {}

    public RegisterDto(String username, String firstName, String lastName, String password, String email, String bornDateString) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.bornDateString = bornDateString;

        final Optional<BornData> bornData = BornData.fromString(bornDateString);
        bornData.ifPresent(data -> this.bornData = data);
    }

    @Override
    public ValidationValue getValidation() {
        final Validation usernameValidation = Validation.USERNAME;
        final Validation nameValidation = Validation.NAME;
        final Validation passwordValidation = Validation.PASSWORD;
        final Validation emailValidation = Validation.EMAIL;
        final Validation ageValidation = Validation.AGE;

        if (!usernameValidation.isValid(username))
            return ValidationValue.buildFrom(usernameValidation);

        if (!nameValidation.isValid(lastName) || !nameValidation.isValid(firstName))
            return ValidationValue.buildFrom(nameValidation);

        if (!passwordValidation.isValid(password))
            return ValidationValue.buildFrom(passwordValidation);

        if (!emailValidation.isValid(email))
            return ValidationValue.buildFrom(emailValidation);

        System.out.println(bornDateString);
        System.out.println(bornData);
//        System.out.println(ageValidation.isValid(bornData.getAge()));
//        System.out.println(bornData.getAge());

        if (bornData == null || !ageValidation.isValid(bornData.getAge()))
            return ValidationValue.buildFrom(ageValidation);

        return ValidationValue.success();
    }

    public UserEntity toEntity() {
        return new UserEntity(
                this.username,
                this.firstName,
                this.lastName,
                this.password,
                this.email
        );
    }
}
