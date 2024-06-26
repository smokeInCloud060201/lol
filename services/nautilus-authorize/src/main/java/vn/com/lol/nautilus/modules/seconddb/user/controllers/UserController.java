package vn.com.lol.nautilus.modules.seconddb.user.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.constants.ControllerPathConstant;

import static vn.com.lol.nautilus.commons.constant.ControllerPathConstant.USER_BASE;

@RestController
@RequestMapping(ControllerPathConstant.API_V1_PREFIX_BASE_PATH + USER_BASE)
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
