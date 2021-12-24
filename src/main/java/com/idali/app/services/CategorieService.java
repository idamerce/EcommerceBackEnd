package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.Categorie;
import com.idali.app.entities.Produit;

 
public interface CategorieService {

	List<Categorie> findAll();
	
	Categorie save(Categorie categorie);
	
	Categorie findById(long id);
	
	void delete(long id);

	List<String> getCategoriesName();

	Categorie getCategorieByName(String nomCategorie);

	List<Produit> getProductsOfCategorie(String nomCategorie);

}
