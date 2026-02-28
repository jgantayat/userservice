package com.learnfirst.userservice.controller;

import com.learnfirst.userservice.dto.UserRequest;
import com.learnfirst.userservice.dto.UserResponse;
import com.learnfirst.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("adduser")
    public ResponseEntity<UserResponse> adduserToDataBase(@RequestBody UserRequest userRequest){
        log.info("REST request to add user");
        UserResponse userResponse = userService.addUserToDatabase(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> listOfAllUsers(){
        log.info("REST request to get all users record");
        List<UserResponse> allUsersFromDatabase = userService.getAllUsersFromDatabase();
        return ResponseEntity.ok(allUsersFromDatabase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") int id){
        log.info("Fetching user with userid = {}", id);
        UserResponse userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUserDetails(
            @PathVariable int id,
            @RequestBody UserRequest userRequest){
        log.info("Updating user details for userId={}", id);
        UserResponse updateUserDetails = userService.updateUserDetails(id, userRequest);
        return ResponseEntity.ok(updateUserDetails);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable int id){
        log.info("Removing user details for userId={}", id);
        userService.removeUserWithId(id);
        return ResponseEntity.noContent().build();
    }

}
