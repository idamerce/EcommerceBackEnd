package com.idali.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idali.app.entities.Role;
import com.idali.app.entities.User;
import com.idali.app.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@PostMapping("/role/create")
	public ResponseEntity<Role> createRole(@RequestBody Role role) {
		roleRepository.save(role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@DeleteMapping("/role/delete/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable Long id) {
		roleRepository.deleteById(id);
		return new ResponseEntity<String>("done", HttpStatus.OK);
	}

	@GetMapping("/role/details/{id}")
	public Role getRole(@PathVariable Long id) {
		if (roleRepository.findById(id).isPresent())
			return roleRepository.findById(id).get();
		else
			return null;
	}

	@GetMapping("/role/all")
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@PutMapping("/role/update/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
		roleRepository.save(role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

}
