package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.UserAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.UserResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.UserRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response.UserResponse;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userResourceAssembler;

    public List<UserResource>  findAll() {
        return userRepository.findAll().stream().map(userResourceAssembler::toModel).collect(Collectors.toList());
    }

    public UserResource findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserResource resource = userResourceAssembler.toModel(user);
        return resource;
    }

    public UserResource save(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();

        User saved = userRepository.save(user);
        UserResource resource = userResourceAssembler.toModel(saved);

        return resource;
    }

    public UserResource update(Long id, UserRequest userRequest) {

        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User user = User.builder()
                .id(id)
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();

        User saved = userRepository.save(user);
        UserResource resource = userResourceAssembler.toModel(saved);

        return resource;
    }

    public Boolean delete(Long id) {

     return userRepository.findById(id).map(user -> {
         userRepository.delete(user);
         return true;
     }).orElse(false);
    }


}
