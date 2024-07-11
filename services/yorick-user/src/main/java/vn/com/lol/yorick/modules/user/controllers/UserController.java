package vn.com.lol.yorick.modules.user.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.controller.BaseController;
import vn.com.lol.common.exceptions.RecordAlreadyExistsException;
import vn.com.lol.yorick.modules.user.dtos.request.RegisteredUser;
import vn.com.lol.yorick.modules.user.dtos.response.UserResponseDTO;
import vn.com.lol.yorick.modules.user.service.UserService;

import java.util.Map;

import static vn.com.lol.common.constants.ControllerPathConstant.API_V1_PREFIX_BASE_PATH;
import static vn.com.lol.yorick.common.constant.ControllerPathConstant.USER_BASE;

@RestController
@RequestMapping(API_V1_PREFIX_BASE_PATH + USER_BASE)
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @PostMapping
    public UserResponseDTO registerUser(@RequestBody RegisteredUser registeredUser) throws RecordAlreadyExistsException {
        return userService.registerUser(registeredUser);
    }

    @GetMapping
    public UserResponseDTO getUserByUserName(@RequestParam("user_name") String userName) {
        return userService.getUserByUserName(userName);
    }

    @PutMapping("/{user_id}")
    public UserResponseDTO updateUserRanking(@PathVariable("user_id") String userName, @RequestBody Map<String, String> parameters) {
        return userService.updateUserRanking(userName, parameters.get("rank"));
    }
}
