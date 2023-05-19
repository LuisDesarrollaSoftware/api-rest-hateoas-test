package com.campodepreubas.apirest.hateoas.apiresthateoastest.controller;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.hateoas.resources.UserResource;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.request.UserRequest;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.dtos.response.UserResponse;
import com.campodepreubas.apirest.hateoas.apiresthateoastest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

   @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResource>> getUsers(){
        return ResponseEntity.ok().body(userService.findAll()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable Long id){
        return ResponseEntity.ok( userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResource> postUser(@RequestBody UserRequest user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> putUser(@PathVariable Long id, @RequestBody UserRequest user){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.update(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id){
        return ResponseEntity.accepted().body(userService.delete(id));
    }
}
