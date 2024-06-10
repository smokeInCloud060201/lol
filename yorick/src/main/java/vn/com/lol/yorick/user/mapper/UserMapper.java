package vn.com.lol.yorick.user.mapper;

import org.springframework.stereotype.Component;
import vn.com.lol.yorick.user.dtos.response.UserResponseDTO;
import vn.com.lol.yorick.user.entities.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private static final String SCOPE = "SCOPE_";
    public static final String ROLE = "ROLE_";

    public UserResponseDTO mapUserToUserResponse(User user) {
        if (user == null) {
            return null;
        }
        return UserResponseDTO.builder()
                .userName(user.getEmail())
                .isEmailVerified(user.isVerifiedEmail())
                .mobileNo(user.getMobileNo())
                .isMobileNoVerified(user.isVerifiedMobileNo())
                .scopes(buildScopes(user))
                .build();
    }

    private List<String> buildScopes(User user) {
        List<String> scopes = new ArrayList<>();
        user.getRoleUsers().forEach(role -> {
            role.getPermissionRoles()
                    .forEach(permission -> scopes.add(SCOPE + permission.getName()));
            scopes.add(ROLE + role.getName());
        });

        return scopes;
    }
}
