package com.cmc.gestion.gestiondoc.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cmc.gestion.gestiondoc.entity.GdTipoDocumental;

@Repository
public interface TipoDocumental extends PagingAndSortingRepository<GdTipoDocumental, Integer> {


}