package com.idali.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idali.app.entities.Produit;
 @Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{

	 
	 @Query("from Produit where designation like : keyword or description like : keyword")
	 public List<Produit> findByKeyword(@Param("keyword") String keyword);
	  
	 @Query("from Produit where categorie.idCategorie = :id")
	 public List<Produit> listProduitsParCategorie(@Param("id") Long id);
	 
	 //TODO changer la conception
	 @Query("from Produit where selected = true")
	 public List<Produit> produitsSelectionne();

	 @Query("from Produit where categorie.nomCategorie = :nomCategorie")
	 public List<Produit> getProductsOfCategorie(@Param("nomCategorie") String nomCategorie);
	 
	 @Query("from Produit where selected = true ")
	 public List<Produit> getSelected();

	 @Query("from Produit where prix between :min and :max")
	 public List<Produit> getProductByPrice(double min, double max);

	 /*
	 @Modifying
	 @Query("update Produit p set p.categorie.idCategorie = :idCategorie where p.idProduit = :idProduit")
	 public void setCategorie(@Param("idProduit") Long idProduit,@Param("idCategorie") Long idCategorie);
 	 */
	 
 
}
