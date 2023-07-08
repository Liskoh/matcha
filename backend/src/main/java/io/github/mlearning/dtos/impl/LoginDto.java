package io.github.mlearning.dtos.impl;

import io.github.mlearning.dtos.IDto;
import io.github.mlearning.dtos.Validation;
import io.github.mlearning.dtos.ValidationValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto implements IDto {

    private String username;
    private String password;

    public LoginDto() {}

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public ValidationValue getValidation() {
        final Validation loginValidation = Validation.USERNAME;
        final Validation passwordValidation = Validation.PASSWORD;

        if (!loginValidation.isValid(username))
            return ValidationValue.buildFrom(loginValidation);

        if (!passwordValidation.isValid(password))
            return ValidationValue.buildFrom(passwordValidation);

        return ValidationValue.success();
    }
}
