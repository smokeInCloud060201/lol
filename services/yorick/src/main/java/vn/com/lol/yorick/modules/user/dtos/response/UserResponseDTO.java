package vn.com.lol.yorick.modules.user.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserResponseDTO {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("mobile_no")
    private String mobileNo;

    @JsonProperty("is_email_verified")
    private boolean emailVerify;

    @JsonProperty("is_mobile_verified")
    private boolean mobileVerify;

    @JsonProperty("scopes")
    private List<String> scopes;
}
