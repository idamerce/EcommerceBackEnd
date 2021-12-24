package com.idali.app.entities;

 
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;




@Entity
public class Categorie implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCategorie;
	//@NotEmpty
	//@Size(min=4,max=20)
	private String nomCategorie;
	//@NotEmpty
	//@Size(min=10)
	private String description;
	
	@Lob
	private byte[] photo;
	private String nomPhoto;
	
	@OneToMany(mappedBy="categorie",fetch = FetchType.LAZY,orphanRemoval=true, cascade=CascadeType.ALL) // juste addedorphal remove and cascade for all
	private List<Produit> produits;
	
	
	public Categorie() {}	
	public Categorie(String nomCategorie) {
		this.nomCategorie= nomCategorie;
	}
	public Categorie(String nomCategorie, String description, byte[] photo, String nomPhoto) {
		super();
		this.nomCategorie = nomCategorie;
		this.description = description;
		this.photo = photo;
		this.nomPhoto = nomPhoto;
	}
	public Categorie(Long id,String nomCategorie, String description, byte[] photo, String nomPhoto) {
		super();
		this.idCategorie = id;
		this.nomCategorie = nomCategorie;
		this.description = description;
		this.photo = photo;
		this.nomPhoto = nomPhoto;
	}
	public Categorie(String nomCategorie, String description, byte[] photo, String nomPhoto,
			List<Produit> produits) {
		super();
		this.nomCategorie = nomCategorie;
		this.description = description;
		this.photo = photo;
		this.nomPhoto = nomPhoto;
		this.produits = produits;
	}
	public Long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getNomPhoto() {
		return nomPhoto;
	}
	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	
	
	
	
	
	
}
