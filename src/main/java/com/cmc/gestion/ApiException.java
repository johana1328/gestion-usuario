package com.cmc.gestion;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiException {
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

      //log errors here
        ValidationErrorDto error = new ValidationErrorDto();
        error.setError("BAD_REQUEST");
        error.setMensaje(fieldErrors.get(0).getDefaultMessage());

        return error;
}

	@ExceptionHandler(ControllerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
	public ValidationErrorDto processErrorControlado(ControllerException ce) {
		
		 ValidationErrorDto error = new ValidationErrorDto();
	        error.setError("BAD_REQUEST");
	        error.setMensaje(ce.getMensaje());
	        
	        return error;
	}

}
