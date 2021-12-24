package com.idali.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idali.app.entities.Categorie;



@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long>{

	@Query("SELECT nomCategorie FROM Categorie")
	List<String> getCategoriesName();
	
	Categorie findBynomCategorie(String nomCategorie);

}
