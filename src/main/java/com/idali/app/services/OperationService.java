package com.idali.app.services;

import java.util.List;

import com.idali.app.entities.Categorie;
import com.idali.app.entities.Commande;
import com.idali.app.entities.Panier;
import com.idali.app.entities.Produit;
import com.idali.app.entities.Role;
import com.idali.app.entities.User;

public interface OperationService {
	
	/* CATEGORIE  */
	public Long ajouterCategorie(Categorie c );
	public List<Categorie> listCategories();
	public Categorie getCategorie(Long idC);
	public void supprimerCategorie(Long idCat);
	public void modifierCategorie(Categorie c);
	/* PRODUIT  */
	public Long ajouterProduit(Produit p, Long  IdCat);
	public List<Produit> listProduits();
	public List<Produit> listProduitsParKeyword(String keyword);
	public List<Produit> listProduitsSelectionne();
	public List<Produit> listProduitsParCategorie(Long idCat);
	public Produit getProduit(Long idPro);
	public void supprimerProduit(Long idPro);
	public void modifierProduit(Produit p);
	
	/* USER  */
	public void ajouterUser(User user );
	public void attribueRole(User user, Role e);
	
	public Commande enregistrerCommande(Panier panier , User client);
}
