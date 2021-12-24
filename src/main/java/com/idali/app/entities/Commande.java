package com.idali.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Commande implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCommande;
	private Date dateCommande;
	
	@OneToMany(mappedBy="commande")
	private List<LigneCommande> items; 
	
	private float totalPrice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idUser")
	private User user;
	
	
	public Commande(Long idCommande, Date dateCommande, List<LigneCommande> items, float totalPrice, User user) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
		this.items = items;
		this.totalPrice = totalPrice;
		this.user = user;
	}
	
	public Commande( Date dateCommande, List<LigneCommande> items, float totalPrice, User user) {
		super();
		this.dateCommande = dateCommande;
		this.items = items;
		this.totalPrice = totalPrice;
		this.user = user;
	}
	public void AddLigneCommande(LigneCommande ligneCommande) {
		if(items==null) {
			items= new ArrayList<>();
		}
		totalPrice+=ligneCommande.getPrix();
		items.add(ligneCommande);
	}
	public void deleteLigneCommande(LigneCommande ligneCommande) {
		if(items==null) {
			items= new ArrayList<>();
		}
		items.remove(ligneCommande);
		
	}

	public Commande(){
		//this.items=new ArrayList<>();
	}
	public Commande(List<LigneCommande> items, User user) {
		this.dateCommande = new Date();
		for(LigneCommande lc:items) {
			totalPrice+=lc.getPrix();
		}
		this.items = items;
		this.user = user;
	}
	/*
	public Commande(User user) {
		this.dateCommande = new Date();
		this.user = user;
		this.totalPrice=0;
		this.items=new ArrayList<>();
	}
	*/
	public Long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public User getUser() {
		try {
			User user = new User(
					this.user.getIdUser(),
					this.user.getNomUser(),
					this.user.getAdresse(), 
					this.user.getTel(),
					this.user.getEmail(),
					this.user.getUserName(), 
					this.user.isActivated() 
					)  ;
			return user;
		 }catch(Exception e) {
			 System.err.println("Error : "+e.getMessage());
		 }
		 return new User();
	}
	
	public void affecterUser(User user) {
		this.user = user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setItems(List<LigneCommande> items) {
		this.items = items;
	}
	
	public List<LigneCommande> getItems() { 
 				List<LigneCommande> lc= this.items;
				lc.forEach(item->{
					try {
						item.setCommande(null);
					}catch(Exception e) {
							System.err.println("LigneCommande->getItems:"+e.getMessage());
					} });
				return lc;
	}
	
	
}
