package com.college.sms.repository;

import com.college.sms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id);

    java.util.List<User> findByUsernameContainingIgnoreCase(String keyword);

    long countByRole(String role);

    long countByEnabled(boolean enabled);

}