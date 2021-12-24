package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.LigneCommande;

public interface LigneCommandeService {
	List<LigneCommande> findAll();
	
	LigneCommande save(LigneCommande ligneCommande);
	
	LigneCommande findById(long idLigneCommande);
	
	void delete(long idLigneCommande);
	
	List<LigneCommande> getUserLigneCommandes();
	List<LigneCommande> getUserValidatedLigneCommandes() ;
	List<LigneCommande> getUserNonValidatedLigneCommandes() ;

	List<LigneCommande> getAllNonValidatedLigneCommandes() ; 
	List<LigneCommande> getAllValidatedLigneCommandes() ;
	
	public void cancelAllOrders();

	


}
