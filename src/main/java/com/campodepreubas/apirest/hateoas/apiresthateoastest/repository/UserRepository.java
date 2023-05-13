package com.campodepreubas.apirest.hateoas.apiresthateoastest.repository;

import com.campodepreubas.apirest.hateoas.apiresthateoastest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

