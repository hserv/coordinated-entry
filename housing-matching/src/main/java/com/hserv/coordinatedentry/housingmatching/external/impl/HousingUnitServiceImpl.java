package com.hserv.coordinatedentry.housingmatching.external.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.hserv.coordinatedentry.housingmatching.external.HousingUnitService;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.model.HousingInventoryModel;
import com.hserv.coordinatedentry.housingmatching.model.UnitWrapper;
import com.servinglynk.hmis.warehouse.client.model.Session;

@Service
public class HousingUnitServiceImpl implements HousingUnitService {

	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	
	@Value("${HOUSING_INVENTORY_MS_URL}")
	private String housingInventoryUrl;
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.constructType(DefaultPrettyPrinter.class);
		objectMapper.writerWithDefaultPrettyPrinter();
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	


	public List<HousingInventory> getHousingInventoryList(String projectType,Session session) {

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(customJackson2HttpMessageConverter());
	//	restTemplate.setMessageConverters(converters);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-HMIS-TrustedApp-Id", "MASTER_TRUSTED_APP");
		headers.add("Authorization","HMISUserAuth session_token="+session.getToken());


		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(housingInventoryUrl)
										.queryParam("inactive", "false")
										.queryParam("projectId", projectType)    // Project type in housing units is same as programtype in eligible clients entity
										.queryParam("vacant", "true");

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response =
		        restTemplate.exchange(builder.build().encode().toUri(),HttpMethod.GET, entity, String.class);

		HousingInventoryModel housingInventoryModel = null;
		List<HousingInventory> housingInventories = new ArrayList<HousingInventory>();
		
		if (null != response && HttpStatus.OK.equals(response.getStatusCode())) {
			System.out.println(response.getBody());
			try {
				JsonNode node  = objectMapper.readTree(response.getBody());
			 housingInventories =	objectMapper.readValue(node.get("content").traverse(), TypeFactory.defaultInstance().constructCollectionType(List.class, HousingInventory.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//			housingInventoryModel = response.getBody();
		}
		
		//List<HousingInventory> housingInventories = new ArrayList<>();
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
