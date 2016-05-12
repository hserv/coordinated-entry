package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;

@Service
public class HousingUnitServiceImpl implements HousingUnitService {

	private RestTemplate restTemplate;
	
	@Value("${HOUSING_INVENTORY_MS_URL}")
	private String housingInventoryUrl;

	@Override
	public List<HousingInventory> getHousingInventoryList( String userId, boolean inactive,
			String projectId) {

		// set the header to accept json
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");

		// set query params in url
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(housingInventoryUrl);

		/*if (!StringUtil.isEmpty(dateCreated)) {
			builder.queryParam("date_created", dateCreated);
		}*/

		/*if (!StringUtils.isEmpty(userId)) {
			builder.queryParam("user_id", userId);
		}*/

		if (inactive) {
			builder.queryParam("inactive", "true");
		}

		/*if (!StringUtils.isEmpty(projectId)) {
			builder.queryParam("project_id", projectId);
		}*/

		//if (vacant) {
			builder.queryParam("vacant", "true");
		//}

		HttpEntity<?> entity = new HttpEntity<>(headers);

		// get the housing inventory list
		/*ResponseEntity<String> response = restTemplate.exchange(builder.build().toUri(),
				HttpMethod.GET, entity, String.classnew ParameterizedTypeReference<List<HousingInventoryModel>>() {
				});
		*/
		ResponseEntity<HousingInventoryModel> response =
		        restTemplate.exchange(builder.build().toUri().toString(),
		                    HttpMethod.GET, entity, HousingInventoryModel.class);

		HousingInventoryModel housingInventoryModel =null;
		if (null != response && HttpStatus.OK.equals(response.getStatusCode())) {
			System.out.println(response.getBody());
			housingInventoryModel = response.getBody();
		}

		return housingInventoryModel.getHousingInventories();
	}

}
