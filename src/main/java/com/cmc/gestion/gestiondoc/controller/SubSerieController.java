package com.cmc.gestion.gestiondoc.controller;

import java.util.Optional;
import javax.validation.Valid;
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
import com.cmc.gestion.gestiondoc.dao.SubserieDao;
import com.cmc.gestion.gestiondoc.entity.GdSerie;
import com.cmc.gestion.gestiondoc.entity.GdSubserie;

@RestController
@RequestMapping("/subserie")
public class SubSerieController {

	@Autowired
	private SubserieDao subserieDao;
	@Autowired
	private SerieDao serieDao;
	
	@GetMapping
	public Iterable<GdSubserie> listarSuberies() {
		Iterable<GdSubserie> resp = this.subserieDao.findAll();
		return resp;
	}
	
	@PostMapping
	public GdSubserie crearSubserie(@Valid @RequestBody GdSubserie subserie) {
		GdSubserie resp = this.subserieDao.save(subserie);
		return resp;
	}
		
		@PutMapping("/{id}")
	    public GdSubserie actulizarSerie(@RequestBody GdSubserie subserie,@PathVariable int id) {
	    	Optional<GdSubserie> optionalGdSubserie = this.subserieDao.findById(id); // Obtiene el valor de la clase
	    	GdSubserie resp = optionalGdSubserie.get();
	    	resp.setNombre(subserie.getNombre());
	    	resp.setProceso(subserie.getProceso());
	    	resp.setProcedimiento(subserie.getProcedimiento());
	    	resp.setRetencionCentral(subserie.getRetencionCentral());
	    	resp.setRetencionGestion(subserie.getRetencionGestion());
	    	resp.setDisposicionFinal(subserie.getDisposicionFinal());
	    	
	    	
	    	GdSerie serie = new GdSerie();
	    	
	    	GdSerie serieEntity = serieDao.getById(serie.getIdSerie());
	    	
	    	
	    	
	    	
	    	
	    	return this.subserieDao.save(resp);
	    	
	    }
		@DeleteMapping("/{id}")
	    public void eliminarSerie(@PathVariable int id) {
		   Optional<GdSubserie> optionalGdSubserie = this.subserieDao.findById(id);
		  if (optionalGdSubserie.isPresent()) {
			  this.subserieDao.deleteById(id);
		} 

		}
}
