package vn.com.lol.yorick.user.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.dto.response.BaseResponse;
import vn.com.lol.controller.BaseController;
import vn.com.lol.mapper.BaseResponseMapper;
import vn.com.lol.yorick.user.dtos.response.UserResponseDTO;
import vn.com.lol.yorick.user.service.UserService;

import static vn.com.lol.constants.ControllerPathConstant.API_V1_PREFIX_BASE_PATH;
import static vn.com.lol.yorick.common.constant.ControllerPathConstant.USER_BASE;

@RestController
@RequestMapping(API_V1_PREFIX_BASE_PATH + USER_BASE)
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping
    public BaseResponse<UserResponseDTO> getUserByUserName(@RequestParam("user_name") String userName) {
        return BaseResponseMapper.of(userService.getUserByUserName(userName));
    }
}
