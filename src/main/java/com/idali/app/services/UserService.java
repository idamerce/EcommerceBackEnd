package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.User;

 
public interface UserService {
	List<User> findAll();
	
	User save(User user);
	
	User findById(long idUser);
	
	void delete(long idUser);


}
