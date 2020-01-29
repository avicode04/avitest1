package com.stackroute.repository;

import com.stackroute.domain.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthUserRepository extends CrudRepository<AuthUser, Integer> {
	
	 AuthUser findByEmailId(String username);
	
}