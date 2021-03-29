package com.cmc.gestion.usuario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.gestion.usuario.bussines.LoginBussines;
import com.cmc.gestion.usuario.dto.LoginDto;

@RestController
public class LoginController {
	
	@Autowired
	private LoginBussines loginBussines;
	
	@PostMapping(path = "/login")
	public LoginDto login(@Valid @RequestBody LoginDto login) {
		
		return loginBussines.login(login);
	}

}
