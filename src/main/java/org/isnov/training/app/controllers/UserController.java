package org.isnov.training.app.controllers;

import org.isnov.training.app.dto.UserResponseDTO;
import org.isnov.training.app.entities.User;
import org.isnov.training.app.entities.UserGroup;
import org.isnov.training.app.models.UserProperty;
import org.isnov.training.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add/group")
    public ResponseEntity<UserGroup> createGroup(@RequestParam String name, @RequestParam String description){
        return ResponseEntity.ok(
                userService.createGroup(name, description)
        );
    }

    @PostMapping("/add/user")
    public ResponseEntity<User> createUser(
            @RequestBody UserResponseDTO dto,
            @RequestParam Long groupId,
            @RequestParam String name,
            @RequestParam String last_name,
            @RequestParam String description) throws Exception {
        return ResponseEntity.ok(
                userService.createUser(groupId, name, last_name, description)
        );
    }

    @PostMapping("/add/user-property/{userId}")
    public ResponseEntity<UserProperty> createUserProperty(
            @PathVariable Long userId,
            @RequestParam String key,
            @RequestParam String value) throws Exception {
        return ResponseEntity.ok(
                userService.createUserProperty(userId, key, value)
        );
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(
                userService.getUserById(userId)
        );
    }

    @GetMapping("/get/user/dto/{userId}")
    public ResponseEntity<UserResponseDTO> getUserByIdWithDto(@PathVariable Long userId){
        return ResponseEntity.ok(
                userService.getUserByIdWithDto(userId)
        );
    }
    @GetMapping("/get/user-property/{userId}")
    public ResponseEntity<List<UserProperty>> getUserPropertyByUser(@PathVariable Long userId){
        return ResponseEntity.ok(
                userService.getUserPropertyByUser(userId)
        );
    }
}
