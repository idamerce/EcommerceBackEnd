package com.idali.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idali.app.entities.Commande;
import com.idali.app.repository.CommandeRepository;
import com.idali.app.repository.LigneCommandeRepository;

@Service
public class ImpCommandeService implements CommandeService{
	
	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	
	@Override
	public List<Commande> findAll() {
		return commandeRepository.findAll();
	}
	
	@Transactional	
	@Override
	public Commande save(Commande commande) {
		commande.affecterUser(authService.LoggedUser());
		commandeRepository.save(commande);
		ligneCommandeRepository.ValidateOrders(authService.LoggedUser().getIdUser(),commande.getIdCommande());		
 		return commande;
	}

	@Override
	public Commande findById(long id) {
		if(commandeRepository.findById(id).isPresent()) {
			return commandeRepository.findById(id).get();
		}
		else return null;
	}

	@Override
	public void delete(long id) {
		commandeRepository.deleteById(id);		
	}

	public List<Commande> findByUser() {
		return commandeRepository.findByUser(authService.LoggedUser()) ;
 	}

}
