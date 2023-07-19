package com.mdts.asset.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.mdts.asset.dto.ErrorField;

public class ErrorFieldConverter {

	/**
	 * convert List of field errors to error field object 
	 * 
	 * @param fieldErrors
	 * @return
	 */
	public static List<ErrorField> convert(List<FieldError> fieldErrors) {
		List<ErrorField> err = new ArrayList<>();
		
		fieldErrors.forEach(fe -> {
			err.add(new ErrorField(fe.getField(), fe.getDefaultMessage()));
		});
		return err;
	}
	
	

}
