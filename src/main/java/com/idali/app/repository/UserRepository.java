package com.idali.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idali.app.entities.User;

 @Repository
public interface UserRepository extends JpaRepository<User,Long>{

		Optional<User> findByUserName(String username);

		@Query("from User where activated = false")
		List<User> getNonActivatedAccounts();
		
		@Query("from User where activated = true")
		List<User> getActivatedAccounts();
		

}
