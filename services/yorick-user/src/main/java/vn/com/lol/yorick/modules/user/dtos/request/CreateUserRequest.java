package vn.com.lol.yorick.modules.user.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static vn.com.lol.yorick.common.constant.DtoValidationErrorMessageConstant.User.PASSWORD_NULL_MSE;
import static vn.com.lol.yorick.common.constant.DtoValidationErrorMessageConstant.User.USERNAME_NULL_MSE;

@Getter
@NoArgsConstructor
public class CreateUserRequest {

    @JsonProperty("user_name")
    @NotNull(message = USERNAME_NULL_MSE)
    private String username;

    @JsonProperty("password")
    @NotNull(message = PASSWORD_NULL_MSE)
    private String password;

    @JsonProperty("mobile_no")
    private String mobileNo;
}
