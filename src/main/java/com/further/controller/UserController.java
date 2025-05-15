package com.further.controller;
import com.further.model.User;
import com.further.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public Integer findIdByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElse(null);
    }
}