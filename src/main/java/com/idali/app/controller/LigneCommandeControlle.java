package com.idali.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idali.app.entities.LigneCommande;
import com.idali.app.services.ImpLigneCommandeService;

@RestController
@RequestMapping("/api/admin/LigneCommandes")
public class LigneCommandeControlle {

	@Autowired
	ImpLigneCommandeService impLigneCommandeService;

	@GetMapping("/getAllLigneCommandes")
	public ResponseEntity<List<LigneCommande>> get() {
		List<LigneCommande> res = impLigneCommandeService.findAll();
		return new ResponseEntity<List<LigneCommande>>(res, HttpStatus.OK);
	}

	@GetMapping("/getUserLigneCommandes")
	public ResponseEntity<List<LigneCommande>> getUserLigneCommandes() {
		List<LigneCommande> res = impLigneCommandeService.getUserLigneCommandes();
		return new ResponseEntity<List<LigneCommande>>(res, HttpStatus.OK);
	}

	@GetMapping("/getUserValidatedLigneCommandes") // TODO
	public ResponseEntity<List<LigneCommande>> getUserValidatedLigneCommandes() {
		List<LigneCommande> res = impLigneCommandeService.getUserValidatedLigneCommandes();
		return new ResponseEntity<List<LigneCommande>>(res, HttpStatus.OK);
	}

	@GetMapping("/getUserNonValidatedLigneCommandes") // TODO
	public ResponseEntity<List<LigneCommande>> getUserNonValidatedLigneCommandes() {
		List<LigneCommande> res = impLigneCommandeService.getUserNonValidatedLigneCommandes();
		return new ResponseEntity<List<LigneCommande>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getAllValidatedLigneCommandes") // TODO
	public ResponseEntity<List<LigneCommande>> getAllValidatedLigneCommandes() {
		List<LigneCommande> res = impLigneCommandeService.getAllValidatedLigneCommandes();
		return new ResponseEntity<List<LigneCommande>>(res, HttpStatus.OK);
	}

	@GetMapping("/getAllNonValidatedLigneCommandes") // TODO
	public ResponseEntity<List<LigneCommande>> getAllNonValidatedLigneCommandes() {
		List<LigneCommande> res = impLigneCommandeService.getAllNonValidatedLigneCommandes();
		return new ResponseEntity<List<LigneCommande>>(res, HttpStatus.OK);
	}

	@PostMapping("/AddLigneCommande")
	public ResponseEntity<LigneCommande> save(@RequestBody LigneCommande ligneCommande) {
		LigneCommande res = impLigneCommandeService.save(ligneCommande);
		return new ResponseEntity<LigneCommande>(res, HttpStatus.OK);
	}

	@GetMapping("/getLigneCommande/{id}")
	public ResponseEntity<LigneCommande> get(@PathVariable("id") Long id) {
		LigneCommande res = impLigneCommandeService.findById(id);
		return new ResponseEntity<LigneCommande>(res, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") long id) {
		impLigneCommandeService.delete(id);
		return new ResponseEntity( HttpStatus.OK); //"LigneCommande of id: " + id + " was deleted"
	}
	
	@DeleteMapping("/cancelAllOrders")
	public ResponseEntity cancelAllOrders() {
		impLigneCommandeService.cancelAllOrders();
		return new ResponseEntity( HttpStatus.OK); //"LigneCommande of id: " + id + " was deleted"
	}
	
}
