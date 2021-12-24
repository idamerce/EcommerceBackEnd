package com.idali.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idali.app.entities.Categorie;
import com.idali.app.entities.Produit;
import com.idali.app.repository.CategorieRepository;
import com.idali.app.repository.ProduitRepository;

@Service
public class ImpCategorieService implements CategorieService {

	
	@Autowired
	CategorieRepository categorieRepository;
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Override
	public List<Categorie> findAll() { 
		return categorieRepository.findAll();
	}

	@Override
	public Categorie save(Categorie categorie) {
		categorieRepository.save(categorie);
		return categorie;
	}

	@Override
	public Categorie findById(long id) {
		if(categorieRepository.findById(id).isPresent()) {
			return categorieRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void delete(long id) {
		categorieRepository.deleteById(id);		
	}

	@Override
	public List<String> getCategoriesName() {
		// TODO Auto-generated method stub
		return categorieRepository.getCategoriesName();		 
	}

	@Override
	public Categorie getCategorieByName(String nomCategorie) {
		// TODO Auto-generated method stub
		return  categorieRepository.findBynomCategorie(nomCategorie);		 
	}

	@Override
	public List<Produit> getProductsOfCategorie(String nomCategorie) {
		return produitRepository.getProductsOfCategorie(nomCategorie);
	}

}
