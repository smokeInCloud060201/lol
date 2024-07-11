package vn.com.lol.yorick.modules.user.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RegisteredUser {
    private String username;
    private String password;
    private String mobileNumber;

}
