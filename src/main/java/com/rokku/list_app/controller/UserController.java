package com.rokku.list_app.controller;

import com.rokku.list_app.dto.user.UserResponse;
import com.rokku.list_app.infra.TokenService;
import com.rokku.list_app.models.User;
import com.rokku.list_app.repository.UserRepository;
import com.rokku.list_app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @GetMapping("user")
    public UserResponse getCurrentUser(HttpServletRequest request) {
        String token = tokenService.recoverToken(request);
        String username = tokenService.validateToken(token);

        if (username == null || username.isEmpty()) {
            return null;
        }

        UserDetails userDetails = userRepository.findByUsername(username);

        if (userDetails == null) {
            throw new RuntimeException("User not found");
        }

        User user = (User) userDetails;

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();

    }


}
