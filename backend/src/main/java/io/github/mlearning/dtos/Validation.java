package io.github.mlearning.dtos;

import lombok.Builder;
import lombok.Getter;

import static io.github.mlearning.dtos.consts.ConstValidation.*;

@Getter
public enum Validation {

//    public static final int OPTIONAL_MAX = -1;

    USERNAME(LOGIN_REGEX, LOGIN_MESSAGE),
    PASSWORD(PASSWORD_REGEX, PASSWORD_MESSAGE),
    NAME(NAME_REGEX, NAME_MESSAGE),
    EMAIL(EMAIL_REGEX, EMAIL_MESSAGE),
    AGE(AGE_MIN, AGE_MAX, AGE_MESSAGE),
    ;

    private final int min;
    private final int max;
    private final String message;
    private final String regex;

    Validation(String regex, String message) {
        this.min = -1;
        this.max = -1;
        this.message = message;
        this.regex = regex;
    }

    Validation(int min, int max, String message) {
        this.min = min;
        this.max = max;
        this.message = message;
        this.regex = "";
    }

    private boolean isSuperior(int number) {
        if (this.max == -1)
            return true;

        return number > this.max;
    }

    private boolean isInferior(int number) {
        return number < this.min;
    }

    public boolean isValid(Object object) {
        if (object == null)
            return false;

        if (object instanceof String) {
            final String value = (String) object;
            return true;
//            return value.matches(this.regex);
        }

        if (object instanceof Integer) {
            final int value = (Integer) object;
            return !this.isSuperior(value) && !this.isInferior(value);
        }

        return false;
    }
}
