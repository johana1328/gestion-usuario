package com.cmc.gestion.usuario.dao;

import java.util.List;
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
	
	@Query(value = "SELECT * FROM us_usuario u"
			+" where CONCAT(u.nombres,' ',u.apellidos) LIKE %:nombre%"
			+ " or u.estado = :estado",
			countQuery = "SELECT COUNT(*) FROM us_usuario u"
					+" where CONCAT(u.nombres,' ',u.apellidos) LIKE %:nombre%"
					+ " or u.estado = :estado"
			,nativeQuery = true)
	public List<Usuario> getUsuariosReporte(@Param("nombre") String nombre,@Param("estado") String estado);
	
	
	public Optional<Usuario>  findByUsuarioAndContrasena(String usuario, String contrasena);
		
	@Query(value = "select * from us_usuario u where estado != 'USBLO'",nativeQuery = true )
	public List<Usuario> getAllUsuarios();
	
	
}
