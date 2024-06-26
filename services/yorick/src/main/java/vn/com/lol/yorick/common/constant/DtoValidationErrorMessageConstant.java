package vn.com.lol.yorick.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DtoValidationErrorMessageConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        public static final String USERNAME_NULL_MSE = "The user name can not be null";
        public static final String PASSWORD_NULL_MSE = "The password can not be null";
    }
}
