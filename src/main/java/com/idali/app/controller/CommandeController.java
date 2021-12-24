package com.idali.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idali.app.entities.LigneCommande;
import com.idali.app.entities.Commande;
import com.idali.app.services.ImpCommandeService;
import com.idali.app.services.ImpLigneCommandeService;

@RestController
@RequestMapping("/api/admin/commandes")
public class CommandeController {
	
	@Autowired
	ImpCommandeService impCommandeService;

	@GetMapping("/getAllCommande")
	public ResponseEntity<List<Commande>> getAll() {
		List<Commande> res = impCommandeService.findAll();
		return new ResponseEntity<List<Commande>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getUserCommandes")
	public ResponseEntity<List<Commande>> getUserCommandes() {
		List<Commande> res = impCommandeService.findByUser();
		return new ResponseEntity<List<Commande>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getCommandeById/{idCommande}")
	public ResponseEntity<Commande> getCommandeById(@PathVariable("idCommande") Long idCommande) {
		Commande res = impCommandeService.findById(idCommande);
		return new ResponseEntity<Commande>(res, HttpStatus.OK);
	}
	
	@PostMapping("/addCommande")
	public ResponseEntity<Commande> save(@RequestBody Commande commande) {
		Commande res = impCommandeService.save(commande);
		return new ResponseEntity<Commande>(res, HttpStatus.OK);
	}

	@PostMapping("/deleteCommaned/{idCommande}")
	public ResponseEntity delete(@PathVariable("idCommande") Long idCommande) {
		impCommandeService.delete(idCommande);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	


}
