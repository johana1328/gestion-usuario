package com.cmc.gestion.usuario.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.cmc.gestion.usuario.entity.Usuario;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, String> {
	
	@Query(value = "SELECT * FROM us_usuario u"
			+" where CONCAT(u.nombres,' ',u.apellidos) LIKE %:nombre%"
			+ " or u.id_usuario = :id",
			countQuery = "SELECT COUNT(*) FROM us_usuario u"
					+" where CONCAT(u.nombres,' ',u.apellidos) LIKE %:nombre%"
					+ " or u.id_usuario = :id"
			,nativeQuery = true)
	public Page<Usuario> getUsuariosPorNombres(@Param("nombre") String nombre,@Param("id") String id, Pageable paginacion);
	
	public Optional<Usuario>  findByUsuarioAndContrasena(String usuario, String contrasena);
		
	
	
}
