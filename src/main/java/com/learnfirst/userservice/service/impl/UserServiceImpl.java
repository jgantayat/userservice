package com.learnfirst.userservice.service.impl;

import com.learnfirst.userservice.dto.UserRequest;
import com.learnfirst.userservice.dto.UserResponse;
import com.learnfirst.userservice.entity.User;
import com.learnfirst.userservice.exception.UserNotFoundException;
import com.learnfirst.userservice.repository.UserServiceRepository;
import com.learnfirst.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServiceRepository userServiceRepository;;
    private final ModelMapper modelMapper;

    @Transactional //GET_BY_ID
    public UserResponse getUserById(int id){
        log.info("Fetching user details from the database for the userId = {}........", id);
        User userOptional = userServiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with userId={}", id);
                    return new UserNotFoundException("User record missing for the id " + id);
                });
       return modelMapper.map(userOptional, UserResponse.class);
    }

    @Transactional //GET_ALL
    public List<UserResponse> getAllUsersFromDatabase(){
        log.info("Fetching all saved user details from the database........");
        List<User> userList = userServiceRepository.findAll();
        if (userList.isEmpty()){
            throw new UserNotFoundException("No users found");
        }
        return  userList.stream().map(user ->modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }
    @Transactional //POST
    public UserResponse addUserToDatabase(UserRequest userRequest){
        log.info("New User Registration Request for the user {}", userRequest.getName());
        User user = modelMapper.map(userRequest, User.class);
        User saved = userServiceRepository.save(user);
        log.info("New User {} registered successful", userRequest.getName());
        return  modelMapper.map(saved, UserResponse.class);
    }

    @Transactional //PUT
    public UserResponse updateUserDetails(int id, UserRequest userRequest){
        log.info("User details update request for the userId {}", id);
        User userOptional = userServiceRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Update failed. User not found for userId={}", id);
                    return new UserNotFoundException("Update failed as User not found with id " + id);
                });

        userOptional.setName(userRequest.getName());
        userOptional.setAge(userRequest.getAge());
        userOptional.setEmail(userRequest.getEmail());
        userOptional.setPhoneNumber(userRequest.getPhoneNumber());
        userOptional.setPassword(userRequest.getPassword());

        User updatedUserDetails = userServiceRepository.save(userOptional);
        log.info("User details updated successfully");
        return modelMapper.map(updatedUserDetails, UserResponse.class);
    }

    @Transactional //DELETE
    public void removeUserWithId(int id){
        log.info("User details removed for the userId {}", id);

        if (!userServiceRepository.existsById(id)) {
            log.warn("Delete failed. User not found for userId={}", id);
            throw new UserNotFoundException("User removal failed as User not found with id " + id);
        }

        userServiceRepository.deleteById(id);
        log.info("USer removed successfully");
    }
}
