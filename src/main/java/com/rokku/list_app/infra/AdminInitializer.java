package com.rokku.list_app.infra;

import com.rokku.list_app.models.User;
import com.rokku.list_app.models.UserRole;
import com.rokku.list_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin") == null) {
            // Criação do administrador
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin")); // Use uma senha forte
            admin.setEmail("admin@domain.com");
            admin.setRole(UserRole.ADMIN);

            // Salva o administrador no banco
            userRepository.save(admin);

            System.out.println("Admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
