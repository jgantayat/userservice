package com.learnfirst.userservice.service;

import com.learnfirst.userservice.dto.UserRequest;
import com.learnfirst.userservice.dto.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    public UserResponse getUserById(int id);
    public List<UserResponse> getAllUsersFromDatabase();
    public UserResponse addUserToDatabase(UserRequest userRequest);
    public UserResponse updateUserDetails(int id, UserRequest userRequest);
    public void removeUserWithId(int id);

}
