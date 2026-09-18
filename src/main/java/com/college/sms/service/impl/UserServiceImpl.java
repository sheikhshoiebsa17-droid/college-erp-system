package com.college.sms.service.impl;

import com.college.sms.entity.User;
import com.college.sms.repository.UserRepository;
import com.college.sms.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByUsernameAndIdNot(String username, Long id) {
        return userRepository.existsByUsernameAndIdNot(username, id);
    }

    @Override
    public List<User> searchUsers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.findByUsernameContainingIgnoreCase(keyword.trim());
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public long getCountByRole(String role) {
        return userRepository.countByRole(role);
    }

    @Override
    public long getActiveUserCount() {
        return userRepository.countByEnabled(true);
    }

    @Override
    public void toggleUserStatus(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(!user.isEnabled());
            userRepository.save(user);
        });
    }

    @Override
    public void createUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user, String newPassword) {
        userRepository.findById(user.getId()).ifPresent(existing -> {
            existing.setUsername(user.getUsername());
            existing.setRole(user.getRole());
            existing.setEnabled(user.isEnabled());

            if (newPassword != null && !newPassword.trim().isEmpty()) {
                existing.setPassword(passwordEncoder.encode(newPassword.trim()));
            }

            userRepository.save(existing);
        });
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }
}