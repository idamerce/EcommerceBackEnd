package com.idali.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idali.app.entities.Role;

 @Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
