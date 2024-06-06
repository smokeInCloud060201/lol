package vn.com.lol.nautilus.auth.service;

import vn.com.lol.exceptions.ResourceExistsException;
import vn.com.lol.nautilus.auth.dtos.request.AuthenticationRequest;
import vn.com.lol.nautilus.auth.dtos.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws ResourceExistsException;
}
