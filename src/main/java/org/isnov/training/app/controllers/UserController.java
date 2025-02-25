package org.isnov.training.app.controllers;

import org.isnov.training.app.dto.*;
import org.isnov.training.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add/group")
    public ResponseEntity<AppResponseDTO> createGroup(@RequestBody AddUserGroupDTO addUserGroupDTO) {
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.createGroup(addUserGroupDTO.getName(), addUserGroupDTO.getDescription())
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);
    }

    @PostMapping("/add/user")
    public ResponseEntity<AppResponseDTO> createUser(
            @RequestBody AddUserDTO addUserDTO
    ) {
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.createUser(addUserDTO.getGroupId(), addUserDTO.getName(), addUserDTO.getLast_name(), addUserDTO.getDescription())
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);
    }

    @PostMapping("/add/user-property/{userId}")
    public ResponseEntity<AppResponseDTO> createUserProperty(
            @PathVariable Long userId,
            @RequestParam String key,
            @RequestParam String value) throws Exception {
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.createUserProperty(userId, key, value)
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<AppResponseDTO> getUserById(@PathVariable Long userId) {

        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.getUserById(userId)
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);
    }

    @GetMapping("/get/user/dto/{userId}")
    public ResponseEntity<AppResponseDTO> getUserByIdWithDto(@PathVariable Long userId) {

        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.getUserByIdWithDto(userId)
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);
    }


    @GetMapping("/get/random")
    public ResponseEntity<AppResponseDTO> random() {

        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.getRandomUser()
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);
    }

    @GetMapping("/get/user-property/{userId}")
    public ResponseEntity<AppResponseDTO> getUserPropertyByUser(@PathVariable Long userId) {

        AppResponseDTO appResponseDTO = new AppResponseDTO();
        try {
            appResponseDTO.setContent(
                    userService.getUserPropertyByUser(userId)
            );
        } catch (Exception e) {
            appResponseDTO.setStatus(-1);
            appResponseDTO.setError(new AppExceptionDTO("6256256", e.getMessage()));
        }
        return ResponseEntity.ok(appResponseDTO);

    }
}
