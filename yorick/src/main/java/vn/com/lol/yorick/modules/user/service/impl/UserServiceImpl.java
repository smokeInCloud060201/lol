package vn.com.lol.yorick.modules.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.lol.yorick.modules.user.entities.User;
import vn.com.lol.yorick.modules.user.mapper.UserMapper;
import vn.com.lol.yorick.modules.user.repositories.UserRepository;
import vn.com.lol.yorick.modules.user.dtos.response.UserResponseDTO;
import vn.com.lol.yorick.modules.user.service.UserService;

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

}
