package com.idali.app.entities;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="user_id")
	private Long idUser;

	private String nomUser;
	private String adresse;
	private String tel;

	private String email;
	private String userName;
	private String password;
	private boolean activated;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	private List<LigneCommande> ligneCommandes = new ArrayList<>();;

 	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	private List<Commande> commandes = new ArrayList<>();;
 	
	
	@ManyToMany(fetch=FetchType.LAZY , cascade =CascadeType.MERGE)//  {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )//{CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinTable( name = "Users_Roles",
			joinColumns =@JoinColumn(name = "id_user",referencedColumnName="idUser") ,
	        inverseJoinColumns = @JoinColumn(name = "id_role",referencedColumnName="idRole")
	)
	private List<Role> roles ;
	
	
	
	
	
	
	public User(){}
	public User(String email, String password) { this.email=email; this.password=password;}
	public User(String nomUser, String adresse, String tel, String email, String userName, String password,
			boolean activated, List<LigneCommande> ligneCommandes, List<Commande> commandes, List<Role> roles) {
		super();
		this.nomUser = nomUser;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.activated = activated;
		this.ligneCommandes = ligneCommandes;
		this.commandes = commandes;
		this.roles = roles;
	}
	public User(String nomUser, String adresse, String tel, String email, String userName, String password,
			boolean activated, List<LigneCommande> ligneCommandes, List<Commande> commandes) {
		super();
		this.nomUser = nomUser;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.activated = activated;
		this.ligneCommandes = ligneCommandes;
		this.commandes = commandes;
	}
	public User(Long idUser,String nomUser, String adresse, String tel, String email, String userName, boolean activated ) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.userName = userName;
 		this.activated = activated;
  	}


	public void addRole(Role role) {
		if(roles==null) {
			roles= new ArrayList<>();
		}
		roles.add(role);

	}
	
	public void deleteRole(Role role) {
		if(roles==null) {
			roles= new ArrayList<>();
		}
		else {
			roles.remove(role);
		} 

	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String ThisPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
 
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

 
	
	

}
