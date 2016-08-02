package com.hserv.coordinatedentry.housinginventory.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hserv.coordinatedentry.housinginventory.web.rest.util.Error;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.Errors;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.ExceptionMapper;

public class BaseResource {

	
	@ExceptionHandler(Throwable.class)
	private Errors handleException(Throwable t, HttpServletRequest request, HttpServletResponse response)  {
	
		ExceptionMapper mapper = new ExceptionMapper();

		ExceptionMapper.Result result = mapper.map(t, request);
		Error er = new Error();
		
		er.setCode(result.getError().getCode());
		er.setMessage(result.getError().getMessage());
		
		
		Errors errors = new Errors();
		List<Error> errs = new ArrayList<Error>();
		errs.add(er);
		
		errors.setErrors(errs);
		
		response.setStatus(result.getStatusCode());
			
		return errors;
	}
	
}