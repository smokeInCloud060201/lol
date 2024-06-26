package vn.com.lol.nautilus.modules.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.dto.response.BaseResponse;
import vn.com.lol.common.constants.ControllerPathConstant;
import vn.com.lol.common.controller.BaseController;
import vn.com.lol.common.exceptions.ResourceExistsException;
import vn.com.lol.nautilus.modules.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;
import vn.com.lol.nautilus.modules.auth.service.AuthService;

import static vn.com.lol.nautilus.commons.constant.ControllerPathConstant.AUTH_BASE;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerPathConstant.API_V1_PREFIX_BASE_PATH + AUTH_BASE)
public class AuthenticationController extends BaseController {

//    private final AuthService authService;
//
//    @PostMapping("/login")
//    public BaseResponse<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request) throws ResourceExistsException {
//        return BaseResponse.<AuthenticationResponse>builder()
//                .data(authService.authenticate(authenticationRequest, request))
//                .build();
//    }
}