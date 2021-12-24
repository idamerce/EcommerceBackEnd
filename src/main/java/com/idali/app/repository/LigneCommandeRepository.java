package com.idali.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idali.app.entities.Commande;
import com.idali.app.entities.LigneCommande;
import com.idali.app.entities.Produit;
import com.idali.app.entities.User;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

	List<LigneCommande> findByUser(User user);

	@Query("from LigneCommande where user.idUser = :id and validationAchat = true")
	List<LigneCommande> getUserValidatedLigneCommandes(@Param("id") Long id);

	@Query("from LigneCommande where user.idUser = :id and validationAchat = false")
	List<LigneCommande> getUserNonValidatedLigneCommandes(@Param("id") Long id);

	@Query("from LigneCommande where  validationAchat = false")
	List<LigneCommande> getAllNonValidatedLigneCommandes();
	
	@Query("from LigneCommande where  validationAchat = true")
	List<LigneCommande> getAllValidatedLigneCommandes();
	
	@Query("select count(*) from LigneCommande where validationAchat = true and produit.idProduit = :id")
	int getProductSolds(@Param("id") Long id);

	@Modifying
	@Query("delete from LigneCommande where user.idUser = :idUser and validationAchat = false")
	void cancelAllOrders(@Param("idUser") Long idUser);
	
	@Modifying
	@Query("update LigneCommande set validationAchat = true , commande.idCommande = :idCommande where user.idUser = :idUser and validationAchat = false ")
	void ValidateOrders(@Param("idUser") Long idUser,@Param("idCommande") Long idCommande);

 
}
