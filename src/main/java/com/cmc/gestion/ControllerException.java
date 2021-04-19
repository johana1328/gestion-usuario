package com.cmc.gestion;

public class ControllerException extends Exception {
	private String mensaje;

	public ControllerException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}


	

}
