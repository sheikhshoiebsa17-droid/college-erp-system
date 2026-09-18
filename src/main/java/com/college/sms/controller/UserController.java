package com.college.sms.controller;

import com.college.sms.entity.User;
import com.college.sms.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // List all users with statistics and optional search keyword
    @GetMapping
    public String viewUsers(@RequestParam(value = "keyword", required = false) String keyword,
                            Model model) {
        List<User> userList = userService.searchUsers(keyword);

        model.addAttribute("userList", userList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalUsers", userService.getUserCount());
        model.addAttribute("adminCount", userService.getCountByRole("ADMIN"));
        model.addAttribute("facultyCount", userService.getCountByRole("FACULTY"));
        model.addAttribute("activeUsers", userService.getActiveUserCount());

        return "user/users";
    }

    // Show form to add a new user
    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        User user = new User();
        user.setEnabled(true);
        user.setRole("FACULTY");

        model.addAttribute("user", user);
        return "user/add-user";
    }

    // Process new user creation
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user,
                           RedirectAttributes redirectAttributes) {

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username cannot be empty.");
            return "redirect:/users/new";
        }

        if (userService.existsByUsername(user.getUsername().trim())) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Username '" + user.getUsername().trim() + "' is already taken. Please choose another.");
            return "redirect:/users/new";
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password cannot be empty.");
            return "redirect:/users/new";
        }

        user.setUsername(user.getUsername().trim());
        userService.createUser(user);

        redirectAttributes.addFlashAttribute("successMessage",
                "User '" + user.getUsername() + "' registered successfully!");
        return "redirect:/users";
    }

    // Show form to edit user
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/users";
        }

        model.addAttribute("user", userOpt.get());
        return "user/edit-user";
    }

    // Process user update
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam(value = "newPassword", required = false) String newPassword,
                             RedirectAttributes redirectAttributes) {

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Username cannot be empty.");
            return "redirect:/users/edit/" + user.getId();
        }

        user.setUsername(user.getUsername().trim());

        if (userService.existsByUsernameAndIdNot(user.getUsername(), user.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Username '" + user.getUsername() + "' is already in use by another account.");
            return "redirect:/users/edit/" + user.getId();
        }

        userService.updateUser(user, newPassword);

        redirectAttributes.addFlashAttribute("successMessage",
                "User '" + user.getUsername() + "' updated successfully!");
        return "redirect:/users";
    }

    // Toggle user enabled/disabled status
    @GetMapping("/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Long id,
                                   Authentication authentication,
                                   RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/users";
        }

        User user = userOpt.get();
        if (authentication != null && user.getUsername().equalsIgnoreCase(authentication.getName()) && user.isEnabled()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "You cannot disable your own active account!");
            return "redirect:/users";
        }

        userService.toggleUserStatus(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Status for user '" + user.getUsername() + "' changed successfully.");
        return "redirect:/users";
    }

    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
            return "redirect:/users";
        }

        User user = userOpt.get();
        if (authentication != null && user.getUsername().equalsIgnoreCase(authentication.getName())) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "You cannot delete your own logged-in account!");
            return "redirect:/users";
        }

        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "User '" + user.getUsername() + "' deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Unable to delete user: " + e.getMessage());
        }

        return "redirect:/users";
    }

    // View current user's profile
    @GetMapping("/profile")
    public String viewProfile(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        User currentUser = userService.findByUsername(username).orElse(null);

        model.addAttribute("currentUser", currentUser);
        return "user/profile";
    }

    // Process profile password change
    @PostMapping("/profile/change-password")
    public String changeProfilePassword(Authentication authentication,
                                        @RequestParam("oldPassword") String oldPassword,
                                        @RequestParam("newPassword") String newPassword,
                                        @RequestParam("confirmPassword") String confirmPassword,
                                        RedirectAttributes redirectAttributes) {
        if (authentication == null) {
            return "redirect:/login";
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "New password and confirm password do not match.");
            return "redirect:/users/profile";
        }

        if (newPassword.trim().length() < 4) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "New password must be at least 4 characters long.");
            return "redirect:/users/profile";
        }

        boolean updated = userService.changePassword(authentication.getName(), oldPassword, newPassword);
        if (!updated) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Current password incorrect. Please try again.");
            return "redirect:/users/profile";
        }

        redirectAttributes.addFlashAttribute("successMessage",
                "Password changed successfully!");
        return "redirect:/users/profile";
    }
}
