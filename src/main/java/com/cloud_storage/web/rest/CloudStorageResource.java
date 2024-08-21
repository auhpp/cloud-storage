package com.cloud_storage.web.rest;

import com.cloud_storage.dto.request.UserRequest;
import com.cloud_storage.dto.response.UserResponse;
import com.cloud_storage.entity.UserVIP;
import com.cloud_storage.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CloudStorageResource {
    private UserRepository userRepository;
    public CloudStorageResource(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        UserVIP userVIP = new UserVIP();
        userVIP.setName(userRequest.getName());
        userVIP.setYob(userRequest.getYob());
        userRepository.save(userVIP);
        return UserResponse.builder().id(userVIP.getId()).name(userVIP.getName()).yob(userVIP.getYob()).build();
    }
}
