package com.idali.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.idali.app.entities.Categorie;
import com.idali.app.entities.Commande;
import com.idali.app.entities.LigneCommande;
import com.idali.app.entities.Produit;
import com.idali.app.entities.Role;
import com.idali.app.entities.User;
import com.idali.app.repository.CategorieRepository;
import com.idali.app.repository.CommandeRepository;
import com.idali.app.repository.LigneCommandeRepository;
import com.idali.app.repository.ProduitRepository;
import com.idali.app.repository.RoleRepository;
import com.idali.app.repository.UserRepository;

@SpringBootApplication
public class ECommerce1Application {

	public static void main(String[] args) {
		
		  SpringApplication.run(ECommerce1Application.class, args);
		
		/*
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ECommerce1Application.class, args);
		
		RoleRepository rolRep = configurableApplicationContext.getBean(RoleRepository.class);
		UserRepository usrRep = configurableApplicationContext.getBean(UserRepository.class); 
		 
		Role rol= new Role("client");
		Role rol1= new Role("manager");
		Role rol11= new Role("admin");
		List<Role> rls = Arrays.asList(rol,rol1,rol11);		
		
		User us= new User("ana@ana.n","anaana");
		User us1= new User("aze@ana.n","azea");
		User us11= new User("fb@ana.n","fb"); 
		List<User> usrs = Arrays.asList(us,us1,us11);
		
		us.addRole(rol11);
		rolRep.save(rol11); 
		usrRep.save(us);
		
		
 
		rol.addUser(us1);
		rol1.addUser(us);
		rol11.addUser(us11);
		rol11.addUser(us1);
		*/
 	
		   /*
			ProduitRepository proRep = configurableApplicationContext.getBean(ProduitRepository.class);
			CategorieRepository catRep = configurableApplicationContext.getBean(CategorieRepository.class); 
			
			Categorie ct1= new Categorie("electronique");
			Categorie ct2= new Categorie("sport");
			
			//catRep.save(ct1); catRep.save(ct2);
			

			Produit pr1= new Produit("t-Shirt",1225);
			Produit pr2= new Produit("socks",11);

			//Produit pr3= new Produit("iPhone",98);
			//Produit pr4= new Produit("Pc",112);
			 
			//pr2.setCategorie(catRep.findById(1l).get()); 
			//pr3.setCategorie(ct2); 
			//pr4.setCategorie(ct2); 
			//List<Produit> pl= Arrays.asList(pr2,pr3,pr4); proRep.saveAll(pl);  
			
			//proRep.save(pr2);  
			
		
		
		/*
		LigneCommandeRepository lgcmdRep = configurableApplicationContext.getBean(LigneCommandeRepository.class);
		CommandeRepository cmdRep = configurableApplicationContext.getBean(CommandeRepository.class); 
		
		User this_user=usrRep.findById(1l).get();
		
		Commande cms= new Commande(this_user);
		
		LigneCommande lgcmd1 = new LigneCommande(proRep.findById(1l).get(), 3, false,
				proRep.findById(1l).get().getPrix(),
				this_user,cms);
		
		cms.AddLigneCommande(lgcmd1);
		
		cmdRep.save(cms);
		lgcmdRep.save(lgcmd1);
		*/
		
		
		
			
 
 		
	}

}
