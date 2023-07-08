package io.github.mlearning.dtos.consts;

import io.github.mlearning.dtos.ValidationValue;

public class ConstValidation {

    /**
     * Login Constants
     **/
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 10;
    public static final String LOGIN_REGEX = "^[a-zA-Z0-9\\-_]{" + USERNAME_MIN_LENGTH + "," + USERNAME_MAX_LENGTH + "}$";

    public static final String LOGIN_MESSAGE = "Login must be between " + USERNAME_MIN_LENGTH + " and " +
            USERNAME_MAX_LENGTH + " characters long and can contain only letters, numbers,underscores and hyphens";

    /**
     * Password Constants
     **/
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 20;
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>\\/?]).{" + PASSWORD_MIN_LENGTH + "," + PASSWORD_MAX_LENGTH + "}$";
    public static final String PASSWORD_MESSAGE = "Password must be between " + PASSWORD_MIN_LENGTH + " and " +
            PASSWORD_MAX_LENGTH + " characters long and must contain at least one uppercase letter, one lowercase letter, one number and one special character";

    /**
     * Name Constants
     **/
    public static final int NAME_MIN_LENGTH = 1;
    public static final int NAME_MAX_LENGTH = 20;
    public static final String NAME_REGEX = "^[a-zA-Z0-9-]{" + NAME_MIN_LENGTH + "," + NAME_MAX_LENGTH + "}$";
    public static final String NAME_MESSAGE = "Name must be between " + NAME_MIN_LENGTH + " and " +
            NAME_MAX_LENGTH + " characters long and can only contain letters, numbers, and hyphens";

    /**
     * Email Constants
     */
    public static final int EMAIL_MIN_LENGTH = 5;
    public static final int EMAIL_MAX_LENGTH = 50;
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]{1,50}@[A-Za-z0-9.-]{" + EMAIL_MIN_LENGTH + "," + EMAIL_MAX_LENGTH + "}$";
    public static final String EMAIL_MESSAGE = "Email must be a valid email address";

    /**
     * Age Constants
     */
    public static final int AGE_MIN = 18;
    public static final int AGE_MAX = 100;
    public static final String AGE_MESSAGE = "Age must be between " + AGE_MIN + " and " + AGE_MAX + " years old";
}
