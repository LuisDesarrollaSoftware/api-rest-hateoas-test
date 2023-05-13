package com.campodepreubas.apirest.hateoas.apiresthateoastest.services;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.assemblers.UserAssembler;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.UserResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.UserRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response.UserResponse;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<List<UserResource>>  findAll() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(userResourceAssembler::toModel).collect(Collectors.toList()));
    }

    public ResponseEntity<UserResource> findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        UserResource resource = userResourceAssembler.toModel(user);
        return ResponseEntity.ok(resource);
    }

    public ResponseEntity<UserResource> save(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();

        User saved = userRepository.save(user);
        UserResource resource = userResourceAssembler.toModel(saved);

        return ResponseEntity.ok(resource);
    }

    public ResponseEntity<UserResource> update(Long id, UserRequest userRequest) {

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

        return ResponseEntity.accepted().body(resource);
    }

    public ResponseEntity<Void> delete(Long id) {

        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);

        return ResponseEntity.accepted().build();
    }


}
