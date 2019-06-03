package com.rso.repository;

import com.rso.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User, Long> {

    User findFirstByNip(@Param("nip") String nip);
}
