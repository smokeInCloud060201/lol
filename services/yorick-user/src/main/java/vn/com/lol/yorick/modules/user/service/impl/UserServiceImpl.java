package vn.com.lol.yorick.modules.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.lol.common.exceptions.RecordAlreadyExistsException;
import vn.com.lol.yorick.modules.user.dtos.request.RegisteredUser;
import vn.com.lol.yorick.modules.user.entities.User;
import vn.com.lol.yorick.modules.user.mapper.UserMapper;
import vn.com.lol.yorick.modules.user.repositories.UserRepository;
import vn.com.lol.yorick.modules.user.dtos.response.UserResponseDTO;
import vn.com.lol.yorick.modules.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElse(null);
        return userMapper.mapUserToUserResponse(user);
    }

    @Override
    public UserResponseDTO updateUserRanking(String userName, String rank) {
        return null;
    }

    @Override
    public UserResponseDTO registerUser(RegisteredUser registeredUser) throws RecordAlreadyExistsException {
        User user = userRepository.findByUserName(registeredUser.getUsername()).orElse(null);
        if (user != null) {
            throw new RecordAlreadyExistsException("User already registered");
        }
        return null;
    }

    public List<UserResponseDTO> searchUsers() {

        return null;
    }

}
