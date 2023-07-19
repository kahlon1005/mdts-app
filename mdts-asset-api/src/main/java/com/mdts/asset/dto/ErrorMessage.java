package com.mdts.asset.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	
	private Date timestamp;
	private int status;
	private String error;
	private String message;
	private List<ErrorField> errorField; 

}
