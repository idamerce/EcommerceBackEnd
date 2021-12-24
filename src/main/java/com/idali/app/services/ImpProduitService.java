package com.idali.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.idali.app.entities.Produit;
import com.idali.app.repository.ProduitRepository;

@Service
public class ImpProduitService implements ProduitService {
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Override
	public List<Produit> findAll() {
		return produitRepository.findAll();
	}

	@Override
	public Produit save(Produit produit) {
		produitRepository.save(produit);
 		return produit;
	}

	@Override
	public Produit findById(long id) {
		if(produitRepository.findById(id).isPresent()) {
			return produitRepository.findById(id).get();
		}
		else return null;
	}

	@Override
	public void delete(long id) {
		produitRepository.deleteById(id);
	}

	@Override
	public List<Produit> findByKeyword(String keyword) {
 		return null ; // TODO produitRepository.findByKeyword("%"+keyword+"%");
	}
	@Override
	public List<Produit> produitsSelectionne() {
 		return produitRepository.produitsSelectionne();
	}

	@Override
	public void updateQuantity(Long id, int quantity) {
		 	Produit produit =this.findById(id);
	    	produit.setQuantite(produit.getQuantite()-quantity);
	    	produitRepository.save(produit);	
	}

	@Override
	public List<Produit> getSelected() {
		// TODO Auto-generated method stub
 		return produitRepository.getSelected();
	}

	@Override
	public List<Produit> getProductByPrice(double min, double max) {
  		return produitRepository.getProductByPrice(min,max);
	}
 
}
