package vn.com.lol.nautilus.modules.auth.service;

import vn.com.lol.exceptions.ResourceExistsException;
import vn.com.lol.nautilus.modules.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.modules.auth.dtos.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws ResourceExistsException;
}
