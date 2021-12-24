package com.idali.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idali.app.entities.Categorie;
import com.idali.app.entities.Commande;
import com.idali.app.entities.Panier;
import com.idali.app.entities.Produit;
import com.idali.app.entities.Role;
import com.idali.app.entities.User;

@Service
public class ImpOperationService implements OperationService{

	@Autowired
	CategorieService categorieService;
	
	@Autowired
	ProduitService produitService;
	
	@Autowired
	UserService userService;
	
	
	
	/* CATEGORIE  */
	
	@Override
	public Long ajouterCategorie(Categorie c) {
		categorieService.save(c);
		return null;
	}

	@Override
	public List<Categorie> listCategories() {
 		return categorieService.findAll();
	}

	@Override
	public Categorie getCategorie(Long idC) {
		return categorieService.findById(idC);
	}

	@Override
	public void supprimerCategorie(Long idCat) {
		categorieService.delete(idCat);
	}

	@Override
	public void modifierCategorie(Categorie c) {
		categorieService.save(c);
	}
	
	
	
	/* PRODUIT  */

	@Override
	public Long ajouterProduit(Produit produit, Long IdCat) {
		
		produit.AddtoCategorie(getCategorie(IdCat));
		produitService.save(produit);
 		return produit.getIdProduit();
	}

	@Override
	public List<Produit> listProduits() {
		return produitService.findAll();
	}

	@Override
	public List<Produit> listProduitsParKeyword(String keyword) {

		return produitService.findByKeyword(keyword);
	}

	@Override
	public List<Produit> listProduitsSelectionne() {
 		return produitService.produitsSelectionne();
	}

	@Override
	public List<Produit> listProduitsParCategorie(Long idCat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit getProduit(Long idPro) {
 		return produitService.findById(idPro);
	}

	@Override
	public void supprimerProduit(Long idPro) {
		produitService.delete(idPro);
	}

	@Override
	public void modifierProduit(Produit produit) {
		produitService.save(produit);
	}

	@Override
	public void ajouterUser(User user) {
		userService.save(user);
	}

	@Override
	public void attribueRole(User user, Role e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Commande enregistrerCommande(Panier panier , User client) {
		// TODO Auto-generated method stub
		return null;
	}

 
 
 
}
