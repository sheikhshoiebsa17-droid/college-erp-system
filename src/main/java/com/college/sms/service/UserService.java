package com.college.sms.service;

import com.college.sms.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByUsername(String username);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    List<User> searchUsers(String keyword);

    long getUserCount();

    long getCountByRole(String role);

    long getActiveUserCount();

    void toggleUserStatus(Long id);

    void createUser(User user);

    void updateUser(User user, String newPassword);

    boolean changePassword(String username, String oldPassword, String newPassword);

}