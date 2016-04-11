package app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.entity.ResponseStorage;
import app.util.WSResponseStatus;

@RestController
public class ResponseRESTImpl {

	@RequestMapping(method = RequestMethod.POST, value="/service/user/response/save")
	public @ResponseBody WSResponseStatus saveUserResponse(@RequestBody ResponseStorage response){
		WSResponseStatus wsResponseStatus = null;
		ResponseStorage response2 = null;

		try{
			wsResponseStatus = new WSResponseStatus();
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
	public @ResponseBody WSResponseStatus updateUserResponse(@RequestBody ResponseStorage response){
		WSResponseStatus wsResponseStatus = null;

		try{
			wsResponseStatus = new WSResponseStatus();

			// responseRepository.save(response);

			wsResponseStatus.setStatusCode("200");
			wsResponseStatus.setStatus("Success");

		}catch(Exception e){
			wsResponseStatus.setErroMessage("Something Wrong in updateUserResponse"+e.getMessage());
		}

		return wsResponseStatus;
	}

}
