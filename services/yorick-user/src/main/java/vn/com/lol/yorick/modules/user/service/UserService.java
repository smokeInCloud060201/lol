package vn.com.lol.yorick.modules.user.service;

import vn.com.lol.common.exceptions.RecordAlreadyExistsException;
import vn.com.lol.yorick.modules.user.dtos.request.RegisteredUser;
import vn.com.lol.yorick.modules.user.dtos.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserByUserName(String userName);

    UserResponseDTO updateUserRanking(String userName, String rank);

    UserResponseDTO registerUser(RegisteredUser registeredUser) throws RecordAlreadyExistsException;
}
