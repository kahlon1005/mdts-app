package com.mdts.asset.exception;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.mdts.asset.dto.ErrorField;
import com.mdts.asset.dto.ErrorMessage;
import com.mdts.asset.util.ErrorFieldConverter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorMessage> handle(HttpServletRequest request, ResponseStatusException e){
		log.error("ResponseStatusException handler :: {}", e.getLocalizedMessage());
		return ResponseEntity.status(e.getStatus())
				.body(ErrorMessage.builder()
						.timestamp(new Date())
						.status(e.getStatus().value())
						.error(e.getStatus().getReasonPhrase())
						.message(e.getReason())
						.build());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handle(HttpServletRequest request, MethodArgumentNotValidException e){
		log.error("MethodArgumentNotValidException handler :: {}", e.getLocalizedMessage());
		
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		
		List<ErrorField> errs = ErrorFieldConverter.convert(fieldErrors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorMessage.builder()
						.timestamp(new Date())
						.status(HttpStatus.BAD_REQUEST.value())
						.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
						.message("Error: Invalid field values")
						.errorField(errs)
						.build());						
	} 
	

}
