package kr.co.dgall.medieye_app3.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.co.dgall.medieye_app3.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
    private MessageSource messageSource;
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ApiResponse<?> handleDataIntegrityViolationException(DataIntegrityViolationException e){
		return ApiResponse.createError(messageSource.getMessage("error.already.exist.email", null, Locale.getDefault()));
	}
	

}
