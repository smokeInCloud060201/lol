package vn.com.lol.nautilus.modules.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import vn.com.lol.common.exceptions.ResourceExistsException;
import vn.com.lol.common.exceptions.ResourceNotFoundException;
import vn.com.lol.nautilus.modules.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest, HttpServletRequest request) throws ResourceExistsException, ResourceNotFoundException;
}
