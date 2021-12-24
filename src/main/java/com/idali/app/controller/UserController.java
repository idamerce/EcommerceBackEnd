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

import com.idali.app.entities.LigneCommande;
import com.idali.app.entities.User;
import com.idali.app.repository.UserRepository;


@RestController
@RequestMapping("/api/admin/users")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	

	
	@PostMapping("/create") 
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userRepository.save(user);
	    return new ResponseEntity<User>(user,HttpStatus.OK);  
	}
	 
	@GetMapping("/details/{id}") 
	public ResponseEntity<User> getUser(@PathVariable Long id) { 
	    if(userRepository.findById(id).isPresent()) { 
	    	User user =userRepository.findById(id).get();
	        return new ResponseEntity<User>(user,HttpStatus.OK) ;
	    }else return  null; 
	}
	 
	@GetMapping("/getAll")
 	public ResponseEntity<List<User>> getUsers() {
		List<User> users=userRepository.findAll();
 	    return new ResponseEntity<List<User>>(users,HttpStatus.OK) ;
 	}
	
	@GetMapping("/getNonActivatedAccounts")
 	public ResponseEntity<List<User>> getNonActivatedAccounts() {
		List<User> users = userRepository.getNonActivatedAccounts();
  	    return new ResponseEntity<List<User>>(users,HttpStatus.OK) ;
 	}
	
	@GetMapping("/getActivatedAccounts")
 	public ResponseEntity<List<User>> getActivatedAccounts() {
		List<User> users = userRepository.getActivatedAccounts();
  	    return new ResponseEntity<List<User>>(users,HttpStatus.OK) ;
 	}
	
	
 	@PutMapping("/update/{id}")
 	public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
 		userRepository.save(user);
 	    return new ResponseEntity(HttpStatus.OK);
 	}
 	
	@GetMapping("/disactivateUser/{id}") 
	public ResponseEntity disactivateUser(@PathVariable Long id) { 
	    if(userRepository.findById(id).isPresent()) { 
	    	User user= userRepository.findById(id).get();
	    	user.setActivated(false);
	    	userRepository.save(user);
	        return new ResponseEntity(HttpStatus.OK);//)<String>("user of id"+id+"has been disactivated successfully",HttpStatus.OK) ;
	    }else return  null; 
	}
	
	@GetMapping("/activateUser/{id}") 
	public ResponseEntity activateUser(@PathVariable Long id) { 
	    if(userRepository.findById(id).isPresent()) { 
	    	User user= userRepository.findById(id).get();
	    	user.setActivated(true);
	    	userRepository.save(user);
	        return new ResponseEntity(HttpStatus.OK) ;
	    }else return  null; 
	}
	
 	@DeleteMapping("/delete/{id}")
 	public ResponseEntity deleteUser(@PathVariable Long id) {
 		userRepository.deleteById(id);
 	    return new ResponseEntity(HttpStatus.OK);
 	}

}
