package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.entity.HouseholdMembership;
import com.hserv.coordinatedentry.housingmatching.service.EligibleClientService;
import com.hserv.coordinatedentry.housingmatching.util.DateUtil;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

public class ClientDEModel {
	
	private Integer veteranStatus;
	private Integer gender;
	private Integer age;
	private UUID clientId;
	private Integer race;
	private boolean servesSingles;
	private Integer targetPopulation;
	private boolean servesHouseholdWithChildren;
	
	private EligibleClientService eligibleClientService;
	
	public Integer getVeteranStatus() {
		return veteranStatus;
	}
	public void setVeteranStatus(Integer veteranStatus) {
		this.veteranStatus = veteranStatus;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getRace() {
		return race;
	}
	public void setRace(Integer race) {
		this.race = race;
	}
	public boolean isServesSingles() {
		return servesSingles;
	}
	public void setServesSingles(boolean servesSingles) {
		this.servesSingles = servesSingles;
	}
		
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public Integer getTargetPopulation() {
		return targetPopulation;
	}
	public void setTargetPopulation(Integer targetPopulation) {
		this.targetPopulation = targetPopulation;
	}
	
	
	public EligibleClientService getEligibleClientService() {
		return eligibleClientService;
	}
	public void setEligibleClientService(EligibleClientService eligibleClientService) {
		this.eligibleClientService = eligibleClientService;
	}
	public boolean isServesHouseholdWithChildren() {
		return servesHouseholdWithChildren;
	}
	public void setServesHouseholdWithChildren(boolean servesHouseholdWithChildren) {
		this.servesHouseholdWithChildren = servesHouseholdWithChildren;
	}
	public void populateValues(BaseClient client){
		if(client.getVeteranStatus()!=null)
			this.setVeteranStatus(Integer.parseInt(client.getVeteranStatus()));
		if(client.getGender()!=null)
			this.setGender(client.getGender());
		if(client.getDob()!=null)
			this.setAge(DateUtil.calculateAge(client.getDob()));
		if(client.getRace()!=null)
			this.setRace(client.getRace());
	}
	
	public void populateValues(Project project){
		if(project.getTargetPopulation()!=null)
			this.setTargetPopulation(project.getTargetPopulation()); 
	}
	
	public void populateValues(EligibleClient client){
		this.setClientId(client.getClientId());
		if(client.getSpdatLabel()!=null) {
			if(client.getSpdatLabel().equalsIgnoreCase("TAY") || client.getSpdatLabel().equalsIgnoreCase("SINGLE_AUDLT")){
				this.setServesSingles(true);
			}else{
				this.setServesSingles(false);
			}
		}
	}
	
	public void populateValues(List<HouseholdMembership> members,String trustedAppId,String sessionToken){
		for(HouseholdMembership membership : members){
			BaseClient client =	eligibleClientService.getClientInfo(membership.getGlobalClientId(), trustedAppId, sessionToken);
				if(client!=null && client.getDob()!=null) 
					 if(DateUtil.calculateAge(client.getDob())  < 18){
						 	this.setServesHouseholdWithChildren(true);
						 	break;
					 }
		}
	}
}