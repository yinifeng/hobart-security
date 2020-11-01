package com.hobart.springmvc.service;

import com.hobart.springmvc.domain.dto.UserDTO;
import com.hobart.springmvc.domain.req.AuthenticationRequest;

public interface AuthenticationService {

    UserDTO authentication(AuthenticationRequest authenticationRequest);
}
