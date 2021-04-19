package com.cmc.gestion.gestiondoc.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cmc.gestion.gestiondoc.entity.GdSerie;

//@RepositoryRestResource(collectionResourceRel = "serie", path = "serie")
@Repository
public interface SerieDao extends JpaRepository<GdSerie, Integer> {

	public SerieDao findByNombre(String nombre);
 

}
