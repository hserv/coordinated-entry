package com.hserv.coordinatedentry.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hserv.coordinatedentry.entity.ResponseStorage;
import com.hserv.coordinatedentry.util.WSResponse;

@RestController
public class ResponseRESTImpl {

	@RequestMapping(method = RequestMethod.POST, value="/service/user/response/save")
	public @ResponseBody WSResponse saveUserResponse(@RequestBody ResponseStorage response){
		WSResponse wsResponseStatus = null;
		ResponseStorage response2 = null;

		try{
			wsResponseStatus = new WSResponse();
			//response2 = responseRepository.save(response);
			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");
			wsResponseStatus.setData(response2);
		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in saveUserResponse API"+e.getMessage());
		}

		return wsResponseStatus;

	}

	@RequestMapping(method = RequestMethod.PUT, value="/user/response/update")
	public @ResponseBody WSResponse updateUserResponse(@RequestBody ResponseStorage response){
		WSResponse wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponse();

			// responseRepository.save(response);

			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in updateUserResponse"+e.getMessage());
		}

		return wsResponseStatus;
	}

}
