package com.idali.app.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idali.app.entities.Categorie;
import com.idali.app.entities.Produit;
import com.idali.app.repository.ProduitRepository;
import com.idali.app.services.CategorieService;
import com.idali.app.services.ImpLigneCommandeService;
import com.idali.app.services.ProduitService;

import net.bytebuddy.utility.RandomString;
@RestController
@RequestMapping("/api/admin/produits")
public class ProduitController {
	
	@Autowired
	ProduitService produitService;
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	CategorieService categorieService;
	
	@Autowired
	ImpLigneCommandeService impLigneCommandeService;
	@Autowired
	ServletContext context;

 
	@PostMapping("/create") 
	public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
		produitService.save(produit);
	    return new ResponseEntity<Produit>(produit,HttpStatus.OK);  
	}
	
	/*
	@GetMapping("/photoProduit/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 System.out.println("image retrieving ");
		 Produit produit   = produitService.findById(id) ;
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+produit.getPhoto()));
	}
	*/
	
	@GetMapping("/getProductByPrice/{min}/{max}")
	 public ResponseEntity<List<Produit>> getProductByPrice(@PathVariable("id") double min,@PathVariable("id") double max) throws Exception{
		 System.out.println("image retrieving ");
		 List<Produit> produits   = produitService.getProductByPrice(min ,max) ;
		    return new ResponseEntity<List<Produit>>(produits,HttpStatus.OK);  
	}
	
	
	@GetMapping("/photoProduit/{id}")
	 public ResponseEntity<Object> getPhoto(@PathVariable("id") Long id) throws Exception{
		Produit produit   = produitService.findById(id) ;
		String image =null;		
		String encodedBase64 =null;
		String filePath=context.getRealPath("/Images");
		try {
			File file  = new File(context.getRealPath("/Images/")+produit.getPhoto());   
			String extension= FilenameUtils.getExtension(file.getName());
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			fileInputStream.read(bytes);
			encodedBase64= Base64.getEncoder().encodeToString(bytes);
			image="data:image/"+extension+";base64,"+encodedBase64;
			fileInputStream.close();
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<Object>(image,HttpStatus.OK);
	}

	//i cancel this here to make it at client side to save some bandwidth
	/*
	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
*/
	
	@PostMapping("/createVersion2") //v2 is with photo
	public ResponseEntity<Produit> createVersion2(@RequestParam("produit") String product,@RequestParam("file") MultipartFile file) 
			throws JsonMappingException, JsonProcessingException {
		
		Produit produit=new ObjectMapper().readValue(product,Produit.class);
		boolean isExist = new File(context.getRealPath("/Images/")).exists();
		if(!isExist) {
			new File(context.getRealPath("/Images/")).mkdir();
		}
		 if (!file.isEmpty()) { 
			String filename= file.getOriginalFilename();

			
			
			UUID uud= java.util.UUID.randomUUID();
	       	String randomStr = uud.toString();
	       // System.out.println("==>"+randomStr);
			String newFilenam = randomStr+"."+FilenameUtils.getExtension(filename);
			File serverFile = new File(context.getRealPath("/Images/"+File.separator+newFilenam));
			
			try {
				FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
			}catch(Exception e) {
				e.printStackTrace();
			}
		 produit.setPhoto(newFilenam); 
		 }
		Produit res = produitService.save(produit);
		return res !=null ?
				new ResponseEntity<Produit>(produit,HttpStatus.OK)  :  new ResponseEntity<Produit>(produit,HttpStatus.BAD_REQUEST);  
	}
	
	@GetMapping("/getProductSolds/{id}") 
	public ResponseEntity<Integer> getProductSolds(@PathVariable Long id) { 
	    	int solds =impLigneCommandeService.getProductSolds(id) ;
	        return new ResponseEntity<Integer>(solds,HttpStatus.OK) ;
	}
	
	@GetMapping("/details/{id}") 
	public ResponseEntity<Produit> getProduit(@PathVariable Long id) { 
	    	Produit produit =produitService.findById(id) ;
	        return new ResponseEntity<Produit>(produit,HttpStatus.OK) ;
	}
	

	@PutMapping("/update/{idProduct}/{idCategorie}")
 	public ResponseEntity updateProduit(@PathVariable Long idCategorie,@PathVariable Long idProduct) {
		Categorie cat = categorieService.findById(idCategorie);
		Produit produit=produitService.findById(idProduct);
		produit.setCategorie(cat);
		produitService.save(produit);
	    return new ResponseEntity(HttpStatus.OK);  
	}
	
/*	@PutMapping("/update/{idCategorie}")
 	public ResponseEntity updateProduit(@RequestBody Produit produit,@PathVariable Long idCategorie) {
		produitService.save(produit);
		Categorie cat = categorieService.findById(idCategorie);
		Produit rec_produit=produitService.findById(produit.getIdProduit());
		rec_produit.setCategorie(cat);
		produitService.save(rec_produit);
	    return new ResponseEntity(HttpStatus.OK);  
	}
*/	
	 
	@GetMapping("/getAll")
 	public ResponseEntity<List<Produit>> getProduits() {
		List<Produit> produits=produitService.findAll();
 	    return new ResponseEntity<List<Produit>>(produits,HttpStatus.OK) ;
 	}
	
	@GetMapping("/getSelected")
 	public ResponseEntity<List<Produit>> getSelected() {
		List<Produit> produits=produitService.getSelected();
 	    return new ResponseEntity<List<Produit>>(produits,HttpStatus.OK) ;
 	}
	
	@GetMapping("/updateQuantity/{id}/{quantity}")
 	public ResponseEntity updateQuantity(@PathVariable Long id, @PathVariable int quantity) {
    	produitService.updateQuantity(id,quantity);
    	return new ResponseEntity(HttpStatus.OK) ;
  	}
	
 	@DeleteMapping("/delete/{id}")
 	public ResponseEntity deleteProduit(@PathVariable Long id) {
 		produitService.delete(id);
 	    return new ResponseEntity(HttpStatus.OK);
 	}

}
