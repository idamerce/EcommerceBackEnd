package com.idali.app.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Role implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRole;
	private String roleName;
	
	
	@ManyToMany(mappedBy="roles",cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
	private List<User> users ;
	
	
	public Role() { }
	
	public Role(String roleName ) { this.roleName=roleName;}
	
	
	public void addUser(User user) {
		
		if(users==null) {
			users= new ArrayList<>();
		}
		users.add(user);		
	}
	
	public void deleteUser(User user) {
		if(users==null) {
			users= new ArrayList<>();
		}
		else { 
			users.remove(user);
		}
 		//user.deleteRole(this);

	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", roleName=" + roleName + ", users=" + users + "]";
	}
	
	
	
	
	
}
