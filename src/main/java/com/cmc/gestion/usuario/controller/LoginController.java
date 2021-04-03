package com.cmc.gestion.usuario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.gestion.usuario.bussines.LoginBussines;
import com.cmc.gestion.usuario.dto.LoginDto;

@RestController
public class LoginController {
	
	@Autowired
	private LoginBussines loginBussines;
	
	@PostMapping(path = "/login")
	@ResponseBody
	public  ResponseEntity<LoginDto> login(@Valid @RequestBody LoginDto login) {
		try {
			LoginDto resp=loginBussines.login(login);
			if(resp != null) {
				return new ResponseEntity<LoginDto>(resp,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		}catch (Exception e) {
			throw e;
		}
	}

}
