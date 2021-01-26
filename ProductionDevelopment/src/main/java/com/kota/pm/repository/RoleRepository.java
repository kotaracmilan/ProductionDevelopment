package com.kota.pm.repository;

import org.springframework.data.repository.CrudRepository;

import com.kota.pm.domain.datatype.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

}
