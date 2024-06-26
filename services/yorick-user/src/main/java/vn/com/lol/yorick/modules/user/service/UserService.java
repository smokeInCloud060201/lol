package vn.com.lol.yorick.modules.user.service;

import vn.com.lol.yorick.modules.user.dtos.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserByUserName(String userName);
}
