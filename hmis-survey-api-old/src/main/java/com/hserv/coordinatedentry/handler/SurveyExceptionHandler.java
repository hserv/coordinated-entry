package com.hserv.coordinatedentry.handler;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hserv.coordinatedentry.exception.AuthorizationException;
import com.hserv.coordinatedentry.exception.InvalidArgumentException;
import com.hserv.coordinatedentry.exception.SurveyControllerException;
import com.hserv.coordinatedentry.util.ApplicationConstants;
import com.hserv.coordinatedentry.util.WSResponse;

@ControllerAdvice
public class SurveyExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody WSResponse processAuthorizationError(AuthorizationException exception) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.setStatusCode(StringUtils.EMPTY + HttpStatus.BAD_REQUEST);
		wsResponse.setStatus(ApplicationConstants.FAILURE);
		wsResponse.setStatusMessage(null);
		wsResponse.setErroMessage(exception.getMessage());
		return wsResponse;
	}
	
	@ExceptionHandler(SurveyControllerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody WSResponse processControllerError(SurveyControllerException exception) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.setStatusCode(StringUtils.EMPTY + HttpStatus.INTERNAL_SERVER_ERROR);
		wsResponse.setStatus(ApplicationConstants.FAILURE);
		wsResponse.setStatusMessage(null);
		wsResponse.setErroMessage(exception.getMessage());
		return wsResponse;
	}
	
	@ExceptionHandler(InvalidArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody WSResponse processCustomValidationError(InvalidArgumentException exception) {
		WSResponse wsResponse = new WSResponse();
		wsResponse.setStatusCode(StringUtils.EMPTY + HttpStatus.BAD_REQUEST);
		wsResponse.setStatus(ApplicationConstants.FAILURE);
		wsResponse.setStatusMessage(null);
		wsResponse.setErroMessage(exception.getMessage());
		return wsResponse;
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody WSResponse processValidationError(MethodArgumentNotValidException exception) {
		BindingResult bindingResult = exception.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		return processFieldError(fieldError);
	}

	private WSResponse processFieldError(FieldError fieldError) {
		if (fieldError == null) {
			return null;
		}

		WSResponse wsResponse = new WSResponse();
		wsResponse.setStatusCode(StringUtils.EMPTY + HttpStatus.BAD_REQUEST);
		wsResponse.setStatus(ApplicationConstants.FAILURE);
		wsResponse.setErroMessage(messageSource.getMessage( fieldError.getDefaultMessage(), null, LocaleContextHolder.getLocale()));

		return wsResponse;

	}
}
