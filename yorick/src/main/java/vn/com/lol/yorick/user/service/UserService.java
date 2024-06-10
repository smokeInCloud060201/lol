package vn.com.lol.yorick.user.service;

import vn.com.lol.yorick.user.dtos.response.UserResponseDTO;
import vn.com.lol.yorick.user.entities.User;

public interface UserService {
    UserResponseDTO getUserByUserName(String userName);
}
