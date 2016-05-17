package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;
import com.hserv.coordinatedentry.housingmatching.model.UnitWrapper;

@Service
public class HousingUnitServiceImpl implements HousingUnitService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${HOUSING_INVENTORY_MS_URL}")
	private String housingInventoryUrl;
	
	@Override
	public List<HousingInventory> getHousingInventoryList(String token ,String projectType) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+token);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(housingInventoryUrl)
										.queryParam("inactive", "false")
										.queryParam("project_id", projectType)
										.queryParam("vacant", "true");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<HousingInventoryModel> response =
		        restTemplate.exchange(builder.build().encode().toUri(),HttpMethod.GET, entity, HousingInventoryModel.class);

		HousingInventoryModel housingInventoryModel = null;
		
		if (null != response && HttpStatus.OK.equals(response.getStatusCode())) {
			System.out.println(response.getBody());
			housingInventoryModel = response.getBody();
		}
		
		List<HousingInventory> housingInventories = new ArrayList<>();
		if(null != housingInventoryModel){
			for(UnitWrapper unitWrapper :housingInventoryModel.getHousingInventories()){
				if(null != unitWrapper){
					HousingInventory housingInventory = unitWrapper.getHousingInventory();
					housingInventories.add(housingInventory);
				}
			}
		}
		return housingInventories;
	}

}
