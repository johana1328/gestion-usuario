package com.cmc.gestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.cmc.gestion.gestiondoc","com.cmc.gestion.usuario"})
public class GestionUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionUsuarioApplication.class, args);
		
	}
	

}
