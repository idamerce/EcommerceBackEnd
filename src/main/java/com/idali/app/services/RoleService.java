package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.Role;

 
public interface RoleService {
	List<Role> findAll();
	
	Role save(Role role);
	
	Role findById(long idRole);
	
	void delete(long idRole);


}
