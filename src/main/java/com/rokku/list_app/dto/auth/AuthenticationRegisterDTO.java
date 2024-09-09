package com.rokku.list_app.dto.auth;

import com.rokku.list_app.models.UserRole;

public record AuthenticationRegisterDTO(String username, String password, String email, UserRole role) {
}
