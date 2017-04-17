package com.nagesh.messagenotifier.DB;

import org.springframework.data.repository.CrudRepository;

import com.nagesh.messagenotifier.entityClass.User;

public interface UserRepository extends CrudRepository<User,String> {

	User findByEmail(String email);
}
