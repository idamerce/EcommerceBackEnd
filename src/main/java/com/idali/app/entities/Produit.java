package com.idali.app.entities;

 
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;






@Entity
public class Produit implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProduit;
	
//TODO	@NotEmpty
	//TODO @Size(min=4,max=20)
	private String dsignation;
 
	private String description;
	//TODO regler l'annotation de prix hibernat aprés
 	private double prix;
	private boolean selected;
	private String photo;
	private int quantite;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCategorie")
//	@Column("idCategorie")
 	private Categorie categorie;

	
	public Produit() {}
	public Produit(String dsignation,double prix) {
		this.dsignation=dsignation;
		this.prix=prix;
	}
	public Produit(String dsignation, String description, double prix, boolean selected, String photo, int quantite,
			Categorie categorie) {
		super();
		this.dsignation = dsignation;
		this.description = description;
		this.prix = prix;
		this.selected = selected;
		this.photo = photo;
		this.quantite = quantite;
		this.categorie = categorie;
	}

	public Produit(String dsignation, String description, double prix, boolean selected, String photo, int quantite) {
		super();
		this.dsignation = dsignation;
		this.description = description;
		this.prix = prix;
		this.selected = selected;
		this.photo = photo;
		this.quantite = quantite;
	}
 
	
	public Produit(Long id,String dsignation, String description, String photo,double prix , int quantite,  Categorie categorie) {
		super();
		this.idProduit = id;
		this.dsignation = dsignation;
		this.description = description;
		this.prix = prix;
		this.categorie = categorie;
		this.photo = photo;
		this.quantite = quantite;
	}
	
	public Long getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}
	public String getDsignation() {
		return dsignation;
	}
	public void setDsignation(String dsignation) {
		this.dsignation = dsignation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public void AddtoCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public Categorie getCategorie() {
		try {
			Categorie categorie = new Categorie(
					this.categorie.getIdCategorie(),
					this.categorie.getNomCategorie(),
					this.categorie.getDescription(),
					this.categorie.getPhoto(),
					this.categorie.getNomPhoto()
					);
			return categorie;
		 }catch(Exception e) {
			 System.err.println("Error : "+e.getMessage());
		 }
		 return new Categorie();

	
	}
	
	public void setCategorie(Categorie categorie) {
		this.categorie = new Categorie();//TODO
		this.categorie=categorie;
	}

	
 
	

}
