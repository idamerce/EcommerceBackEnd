package com.idali.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idali.app.entities.Commande;
import com.idali.app.entities.User;

 @Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {

	List<Commande> findByUser(User user);

}
