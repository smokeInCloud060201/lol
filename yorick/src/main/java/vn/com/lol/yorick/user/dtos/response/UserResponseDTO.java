package vn.com.lol.yorick.user.dtos.response;

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
    private boolean isEmailVerified;

    @JsonProperty("is_mobile_no_verified")
    private boolean isMobileNoVerified;

    @JsonProperty("scopes")
    private List<String> scopes;
}
