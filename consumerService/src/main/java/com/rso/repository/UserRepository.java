package com.rso.repository;

import com.rso.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    User findFirstByNipNumber(@Param("nipNumber") String nipNumber);
}
