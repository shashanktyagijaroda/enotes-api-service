package com.shashank.handler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {

	private String status;
	
	private String mesaage;
	
	private Object data;
	
	private HttpStatus responseStatus;
	

}
