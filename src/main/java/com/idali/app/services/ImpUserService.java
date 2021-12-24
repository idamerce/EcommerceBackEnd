package com.idali.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idali.app.entities.User;
import com.idali.app.repository.RoleRepository;
import com.idali.app.repository.UserRepository;

@Service
public class ImpUserService implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
 		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		userRepository.save(user);
 		return user;
	}

	@Override
	public User findById(long id) {
		if(userRepository.findById(id).isPresent()) {
			return userRepository.findById(id).get();
		}
		else return null;
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);		
	}

	public User findByUserName(String userName) { 
		return userRepository.findByUserName(userName).orElse(null); 
	}

}
