package com.idali.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idali.app.entities.LigneCommande;
import com.idali.app.repository.LigneCommandeRepository;

@Service
public class ImpLigneCommandeService implements LigneCommandeService {

	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	AuthService authService;
	@Autowired
	ImpUserService userService;
	
	@Override
	public List<LigneCommande> findAll() {
 		return  ligneCommandeRepository.findAll();
	}

	@Override
	public LigneCommande save(LigneCommande lignecommande) { 
		//User user =   authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No user logged in")); 
		//com.idali.app.entities.User looggedUser = userService.findByUserName(user.getUsername());
		lignecommande.affecterParUser(authService.LoggedUser());
		ligneCommandeRepository.save(lignecommande);
		return lignecommande;
	}

	@Override
	public LigneCommande findById(long id) {
		if(ligneCommandeRepository.findById(id).isPresent()) {
			return ligneCommandeRepository.findById(id).get();
		}
		else return null;
	}

	@Override
	public void delete(long id) {
		ligneCommandeRepository.deleteById(id);		
	}

	public List<LigneCommande> getUserLigneCommandes() {
		//User user =  authService.getCurrentUser().orElseThrow(()->new IllegalArgumentException("No user logged in")); 
		//com.idali.app.entities.User looggedUser = userService.findByUserName(user.getUsername()); 
 		return ligneCommandeRepository.findByUser(authService.LoggedUser());		
	}

	public List<LigneCommande> getUserValidatedLigneCommandes() {
		return ligneCommandeRepository.getUserValidatedLigneCommandes(authService.LoggedUser().getIdUser());
	}

	public List<LigneCommande> getUserNonValidatedLigneCommandes() {
		return ligneCommandeRepository.getUserNonValidatedLigneCommandes(authService.LoggedUser().getIdUser());
	}

	@Override
	public List<LigneCommande> getAllNonValidatedLigneCommandes() {
		return ligneCommandeRepository.getAllNonValidatedLigneCommandes();

	}

	@Override
	public List<LigneCommande> getAllValidatedLigneCommandes() {
		return ligneCommandeRepository.getAllValidatedLigneCommandes();

	}

	public int getProductSolds(Long id) {
 		return ligneCommandeRepository.getProductSolds(id);
	}
	
	@Transactional
	public void cancelAllOrders() {
		ligneCommandeRepository.cancelAllOrders(authService.LoggedUser().getIdUser());
	}
	
	

}
