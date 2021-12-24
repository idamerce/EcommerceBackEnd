package com.idali.app.entities;

import java.io.Serializable;
 
 import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 

@Entity
public class LigneCommande implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idProduit")
	private Produit produit;
	
	private int quantite;
	private boolean validationAchat;
	private double prix;
	 	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="idCommande")
	private Commande commande;

	public LigneCommande() {}
	public LigneCommande(Produit produit, int quantite, boolean validationAchat, double prix, User user,
			Commande commande) {
		super();
		this.produit = produit;
		this.quantite = quantite;
		this.validationAchat = validationAchat;
		this.prix = prix*quantite;
		this.user = user;
		this.commande = commande;
	}
	public LigneCommande(Produit produit, boolean validationAchat, double prix, User user,
			Commande commande) {
		super();
		this.produit = produit;
		this.quantite = 1;
		this.validationAchat = false;
		this.prix = prix*this.quantite;
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public Long getProduit() {
		return produit.getIdProduit();
	}*/
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean isValidationAchat() {
		return validationAchat;
	}
	public void setValidationAchat(boolean validationAchat) {
		this.validationAchat = validationAchat;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	
	
	public void affecterParUser(User user) {
		this.user= user;
	}
	public Long getUser() {
		return user.getIdUser()  ;
	}
	public Commande getCommande() {
		return commande ; //return commande;
	}
	
	public Produit getProduit() {
		try {
			Produit produit = new Produit(
					this.produit.getIdProduit(),					
					this.produit.getDsignation(),
					this.produit.getDescription(),
					this.produit.getPhoto(),
					this.produit.getPrix(),
					this.produit.getQuantite(),
					this.produit.getCategorie()
					);
			return produit;
		 }catch(Exception e) {
			 System.err.println("Error : "+e.getMessage());
		 }
		 return new Produit();

	
	}
	
	
	
	
}
