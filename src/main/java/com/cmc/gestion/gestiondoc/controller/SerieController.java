package com.cmc.gestion.gestiondoc.controller;
//prueba git 
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.gestion.gestiondoc.dao.SerieDao;
import com.cmc.gestion.gestiondoc.entity.GdSerie;

@RestController
@RequestMapping("/serie")
public class SerieController {
	
	@Autowired
	private SerieDao serieDao;
    
	@GetMapping
	public Iterable<GdSerie> listarSeries() {
		Iterable<GdSerie> resp = this.serieDao.findAll();
		return resp;
	}
	
	@PostMapping
	public GdSerie crearSerie(@Valid @RequestBody GdSerie serie) {
		GdSerie resp = this.serieDao.save(serie);
		return resp;
		
	}
	@GetMapping("/{id}")
	public GdSerie getByIdSeerie(@RequestBody GdSerie serie,@PathVariable int id) {
		Optional<GdSerie> optionalGdSerie = this.serieDao.findById(id); // Obtiene el valor de la clase
    	GdSerie resp = optionalGdSerie.get();
    	resp.setNombre(serie.getNombre());
    	resp.setDescripcion(serie.getDescripcion());
    	  return this.serieDao.save(resp);
	}
	
    @PutMapping("/{id}")
    public GdSerie actulizarSerie(@RequestBody GdSerie serie,@PathVariable int id) {
    	Optional<GdSerie> optionalGdSerie = this.serieDao.findById(id); // Obtiene el valor de la clase
    	GdSerie resp = optionalGdSerie.get();
    	resp.setNombre(serie.getNombre());
    	resp.setDescripcion(serie.getDescripcion());
    	  return this.serieDao.save(resp);
    	
    }
    
   @DeleteMapping("/{id}")
    public void eliminarSerie(@PathVariable int id) {
	   Optional<GdSerie> optionalGdSerie = this.serieDao.findById(id);
	  if (optionalGdSerie.isPresent()) {
		  this.serieDao.deleteById(id);
	} 
	   
    	
    }
}
