package com.college.sms.config;

import com.college.sms.entity.User;
import com.college.sms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initDefaultAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("Sheikh")) {
                User sheikh = new User();
                sheikh.setUsername("Sheikh");
                sheikh.setPassword(passwordEncoder.encode("Shoieb@27#09"));
                sheikh.setRole("ADMIN");
                sheikh.setEnabled(true);
                userRepository.save(sheikh);
                log.info("Default admin user 'Sheikh' created successfully.");
            }

            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);
                log.info("Default admin user 'admin' created successfully.");
            }
        };
    }
}
