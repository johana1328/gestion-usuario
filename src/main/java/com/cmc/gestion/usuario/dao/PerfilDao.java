package com.cmc.gestion.usuario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmc.gestion.usuario.entity.Perfil;

public interface PerfilDao extends JpaRepository<Perfil,String > {

}
