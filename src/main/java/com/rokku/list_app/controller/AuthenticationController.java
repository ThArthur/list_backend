package com.rokku.list_app.controller;

import com.rokku.list_app.dto.auth.AuthenticationLoginDTO;
import com.rokku.list_app.dto.auth.AuthenticationRegisterDTO;
import com.rokku.list_app.dto.auth.LoginResponseDTO;
import com.rokku.list_app.infra.TokenService;
import com.rokku.list_app.models.User;
import com.rokku.list_app.models.UserRole;
import com.rokku.list_app.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<?> loginRequest(@RequestBody @Valid AuthenticationLoginDTO data) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        var auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        System.out.println(auth.getAuthorities());
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public String createEncodedPassword(String username, String password) {
        if (userRepository.findByUsername(username) != null) return null;

        return new BCryptPasswordEncoder().encode(password);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid AuthenticationRegisterDTO data) {

        String token = createEncodedPassword(data.username(), data.password());

        User newUser = new User(data.username(), token, data.email(), UserRole.USER);

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/create-user")
    public ResponseEntity<?> register(@RequestBody @Valid AuthenticationRegisterDTO data) {
        String token = createEncodedPassword(data.username(), data.password());

        User newUser = new User(data.username(), token, data.email(), data.role());

        userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
