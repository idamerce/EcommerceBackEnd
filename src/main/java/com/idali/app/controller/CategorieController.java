package com.idali.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idali.app.entities.Categorie;
import com.idali.app.entities.Produit;
import com.idali.app.services.CategorieService;

@RestController
@RequestMapping("/api/admin/categories")
public class CategorieController {

	
	@Autowired
	CategorieService categorieService;

	@PostMapping("/create") 
	public ResponseEntity<Categorie> createProduit(@RequestBody Categorie categorie) {
		categorieService.save(categorie);
	    return new ResponseEntity<Categorie>(categorie,HttpStatus.OK);  
	}
	 
	@GetMapping("/details/{id}") 
	public ResponseEntity<Categorie> getProduit(@PathVariable Long id) { 
		Categorie categorie =categorieService.findById(id) ;
	        return new ResponseEntity<Categorie>(categorie,HttpStatus.OK) ;
	}
	
	@GetMapping("/getCategorieByName/{nomCategorie}") 
	public ResponseEntity<Categorie> getCategorieByName(@PathVariable String nomCategorie) { 
		Categorie categorie =categorieService.getCategorieByName(nomCategorie) ;
	        return new ResponseEntity<Categorie>(categorie,HttpStatus.OK) ;
	}
	 
	@GetMapping("/getAll")
 	public ResponseEntity<List<Categorie>> getProduits() {
		List<Categorie> categorie=categorieService.findAll();
 	    return new ResponseEntity<List<Categorie>>(categorie,HttpStatus.OK) ;
 	}
	
	@GetMapping("/getCategoriesName")
 	public ResponseEntity<List<String>> getCategoriesName() {
		List<String> categories_name=categorieService.getCategoriesName();
 	    return new ResponseEntity<List<String>>(categories_name,HttpStatus.OK) ;
 	    
 	}
	
	@GetMapping("/getProductsOfCategorie/{nomCategorie}")
 	public ResponseEntity<List<Produit>> getProductsOfCategorie(@PathVariable String nomCategorie) {
		List<Produit> produits = categorieService.getProductsOfCategorie(nomCategorie);
 	    return new ResponseEntity<List<Produit>>(produits,HttpStatus.OK) ;
 	    
 	}
	 
 
 	@PutMapping("/update")
 	public ResponseEntity updateProduit(@RequestBody Categorie categorie) {
 		categorieService.save(categorie);
 	    return new ResponseEntity(HttpStatus.OK);
 	}
	
 	@DeleteMapping("/delete/{id}")
 	public ResponseEntity deleteProduit(@PathVariable Long id) {
 		categorieService.delete(id);
 	    return new ResponseEntity(HttpStatus.OK);
 	}
 	
}
