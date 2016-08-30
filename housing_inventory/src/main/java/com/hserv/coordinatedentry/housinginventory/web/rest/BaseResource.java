package com.hserv.coordinatedentry.housinginventory.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hserv.coordinatedentry.housinginventory.web.rest.util.Error;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.Errors;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.ExceptionMapper;

public class BaseResource {
	
	
	
	public String getAuthorizationHeader(HttpServletRequest request){
		String sessionToken = null;
		for (Method method : methodOrder) {
			sessionToken = retrieveSessionToken(request, method);
			if (sessionToken != null) {
				break;
			}
		}
		return sessionToken;
	}
	
	private String retrieveSessionToken(HttpServletRequest request, Method method) {
		String headerName = "Authorization";
		String parameterName = "authentication_token";
		String cookieName = "authentication_token";
		String headerRegex = "[\\s|=|\"]+";
		String authRealm = "HMISUserAuth";
		String oAuthRealm = "Bearer";

		String sessionToken = null;
		switch (method) {
		case Header:
			String headerValue = request.getHeader(headerName);
			if (headerValue != null) {
				String[] split = headerValue.split(headerRegex);
				if (split != null)	{
					if ((split.length >= 3) && split[0].equalsIgnoreCase(authRealm))	{
						sessionToken = split[2];
					}
					else if ((split.length >= 2) && split[0].equalsIgnoreCase(oAuthRealm))	{
						sessionToken = split[1];
					}
				}
			}
			break;
		case Query:
			sessionToken = request.getParameter(parameterName);
			break;
		case Cookie:
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(cookieName)) {
						sessionToken = cookie.getValue();
					}
				}
			}
			break;
		}
		return sessionToken;
	}

	
	
	public String getTrustedApp(HttpServletRequest request){
		String trustedAppId = null;
		for (Method method : methodOrder) {
			trustedAppId = retrieveTrustedApp(request, method);
			if (trustedAppId != null) {
				break;
			}
		}
		return trustedAppId;
	}
	
	private String retrieveTrustedApp(HttpServletRequest request, Method method) {
		 String headerName = "X-HMIS-TrustedApp-Id";
		 String parameterName = "trustedApp_id";
		String trustedAppId = null;
		switch (method) {
		case Header:
			trustedAppId = request.getHeader(headerName);
			break;
		case Query:
			trustedAppId = request.getParameter(parameterName);
			break;
		}
		if (trustedAppId != null) {
			trustedAppId = trustedAppId.trim();
		}
		return trustedAppId;
	}
	
	private static enum Method {
		Query, Header,Cookie
	};


	private Method[] methodOrder = new Method[] { Method.Query, Method.Header ,Method.Cookie};

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Errors processValidationError(MethodArgumentNotValidException ex,HttpServletRequest request,HttpServletResponse response) {
		Errors errors = new Errors();
		
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for(FieldError fieldError : fieldErrors){
        	Error error = new Error();
        	error.setMessage(fieldError.getDefaultMessage());
        	errors.addError(error);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return errors;
    }
	
	
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