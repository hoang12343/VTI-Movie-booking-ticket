package vti_galaxy.service.auth;

import vti_galaxy.modal.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password);
}
