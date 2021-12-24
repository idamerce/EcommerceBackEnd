package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.Produit;

 
public interface ProduitService {
	
	List<Produit> findAll();
	
	Produit save(Produit produit);
	
	Produit findById(long idProduit);
	
	void delete(long idProduit);
	
	List<Produit> findByKeyword(String keyword);
	List<Produit> produitsSelectionne();

	void updateQuantity(Long id, int quantity);

	List<Produit> getSelected();

	List<Produit> getProductByPrice(double min, double max);

}
