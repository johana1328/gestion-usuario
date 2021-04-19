package com.cmc.gestion.gestiondoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.gestion.gestiondoc.entity.GdSubserie;



	//@RepositoryRestResource(collectionResourceRel = "subserie", path = "subserie")
	public interface SubserieDao  extends JpaRepository<GdSubserie, Integer> {

	 

}
