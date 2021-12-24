package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.Commande;

 
public interface CommandeService {
	
	List<Commande> findAll();
	
	Commande save(Commande commande);
	
	Commande findById(long idCommande);
	
	void delete(long idCommande);


}
