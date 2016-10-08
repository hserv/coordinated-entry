package com.hserv.coordinatedentry.housingmatching.helper;


import java.security.InvalidParameterException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.servinglynk.hmis.warehouse.core.model.Error;


public class ExceptionMapper {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	
	// error codes
	public static final String ERR_CODE_UNKNOWN								= "UNKNOWN_ERROR";
	public static final String ERR_CODE_DEVELOPER_COMPANY_NOT_FOUND         = "DEVELOPER_COMPANY_NOT_FOUND";
	public static final String ERR_CODE_PICK_LIST_GROUP_NOT_FOUND 		    = "PICK_LIST_GROUP_NOT_FOUND";
	public static final String ERR_CODE_PICK_LIST_VALUE_NOT_FOUND 		    = "PICK_LIST_VALUE_NOT_FOUND";
	public static final String ERR_CODE_QUESTION_GROUP_NOT_FOUND 		    = "QUESTION_GROUP_NOT_FOUND";	
	public static final String ERR_CODE_QUESTION_NOT_FOUND 		            = "QUESTION_NOT_FOUND";	
	public static final String ERR_CODE_RESOURCE_NOT_FOUND 		            = "RESOURCE_NOT_FOUND";	
	public static final String ERR_CODE_SECTION_QUESTION_MAPPING_NOT_FOUND 	= "SECTION_QUESTION_MAPPING_NOT_FOUND";	
	public static final String ERR_CODE_SECTION_SCORE_NOT_FOUND 		    = "SECTION_SCORE_NOT_FOUND";	
	public static final String ERR_CODE_SURVEY_NOT_FOUND 		            = "SURVEY_NOT_FOUND";	
	public static final String ERR_CODE_SURVEY_SECTION_NOT_FOUND 		    = "SURVEY_SECTION_NOT_FOUND";	
	
	public static final String ERR_CODE_INVALID_PARAMETER					= "INVALID_PARAMETER";
	
	
	// error messages
	public static final String ERR_MSG_UNKNOWN = "unexpected error occurred";

	// query parameter names
	public static final String PARAM_NAME_SC_200_ONLY = "sc200Only";

	private boolean internalErrorMessageReturned;

	public Result map(Throwable th, HttpServletRequest request) {
		
		Result r = new Result();

		try {

			throw th;
		}catch (HttpClientErrorException ex) {
			logger.info(ex.getMessage());
			logger.error(ex.getMessage(), ex);
			r.setErrorCode("REQUEST_AUTHENTICATION_FAILED");
			r.setErrorMessage("REQUEST_AUTHENTICATION_FAILED");
			r.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
		}catch(InvalidMatchStatus ex){
			logger.info(ex.getMessage());
			logger.error(ex.getMessage(), ex);
			r.setErrorCode("ERR_CODE_INVALID_STATUS");
			r.setErrorMessage(ex.getMessage());
			r.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);			
		}catch (org.springframework.http.converter.HttpMessageNotReadableException ex) {
			logger.info(ex.getMessage());
			logger.error(ex.getMessage(), ex);
			r.setErrorCode("ERR_CODE_INVALID_REQUEST");
			r.setErrorMessage("Invalid request body");
			r.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);			
		}
		catch(InvalidParameterException ex){
			logger.info(ex.getMessage());
			logger.error(ex.getMessage(), ex);
			r.setErrorCode(ERR_CODE_INVALID_PARAMETER);
			r.setErrorMessage(ex.getMessage());
			r.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}catch(ResourceNotFoundException ex){
			logger.info(ex.getMessage());
			logger.error(ex.getMessage(), ex);
			r.setErrorCode(ERR_CODE_RESOURCE_NOT_FOUND);
			r.setErrorMessage(ex.getMessage());
			r.setStatusCode(HttpServletResponse.SC_NOT_FOUND);
		}catch (InvalidFormatException e) {
			logger.info(e.getMessage());
			logger.error(e.getMessage(),e);
			r.setErrorCode("ERR_CODE_INVALID_DATA");
			r.setErrorMessage("Request contains invalid data");
			r.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		}
		catch (Throwable t) {
        	t.printStackTrace();
			logger.info(t.getMessage(), t);

			if (t.getCause().getClass().getName().endsWith("UnmarshallingFailureException"))	{
				r.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
				r.setErrorCode(ERR_CODE_INVALID_PARAMETER);
				r.setErrorMessage("Invalid payload");
        	}
        	else	{
				r.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				r.setErrorCode(ERR_CODE_UNKNOWN);
				if (isInternalErrorMessageReturned()) {
					r.setErrorMessage(ERR_MSG_UNKNOWN + " : " + t.getMessage());
				} else {
					r.setErrorMessage(ERR_MSG_UNKNOWN);
				}
        	}
		}

		if (returnStatusCode200Only(request)) {
			r.setStatusCode(HttpServletResponse.SC_OK);
		}

		return r;
	}

	@SuppressWarnings("rawtypes")
	private boolean returnStatusCode200Only(HttpServletRequest request) {
		boolean sc200Only = false;
		Map parameterMap = request.getParameterMap();
		if (parameterMap != null) {
			sc200Only = parameterMap.keySet().contains(PARAM_NAME_SC_200_ONLY);
		}

		return sc200Only;
	}
	
	public boolean isInternalErrorMessageReturned() {
		return internalErrorMessageReturned;
	}

	public void setInternalErrorMessageReturned(boolean internalErrorMessageReturned) {
		this.internalErrorMessageReturned = internalErrorMessageReturned;
	}

	/************************** Nested Classes *******************************/

	public static class Result {

		private int statusCode;
		private Error error = new Error();

		public int getStatusCode() {

			return statusCode;

		}

		public void setStatusCode(int statusCode) {

			this.statusCode = statusCode;

		}

		public Error getError() {

			return error;

		}

		public void setErrorCode(String errorCode) {

			this.error.setCode(errorCode);

		}

		public void setErrorMessage(String errorMessage) {

			this.error.setMessage(errorMessage);

		}

	}

}
