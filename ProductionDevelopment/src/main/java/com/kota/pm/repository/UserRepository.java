package com.kota.pm.repository;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
