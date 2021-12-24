package com.idali.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idali.app.entities.Role;
import com.idali.app.repository.ProduitRepository;
import com.idali.app.repository.RoleRepository;

@Service
public class ImpRoleService implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Role save(Role role) {
		roleRepository.save(role);	
		return role;
	}

	@Override
	public Role findById(long id) {
		if(roleRepository.findById(id).isPresent()) {
			return roleRepository.findById(id).get();
		}
		else return null;
	}

	@Override
	public void delete(long id) {
		roleRepository.deleteById(id);		
	}

}
