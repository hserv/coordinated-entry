package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.hserv.coordinatedentry.housingmatching.entity.EligibleClient;
import com.hserv.coordinatedentry.housingmatching.util.DateUtil;
import com.servinglynk.hmis.warehouse.core.model.BaseClient;

public class ClientDEModel {
	
	private Integer veteranStatus;
	private Integer gender;
	private Integer age;
	
	private boolean servesSingles;
	
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
	
	public boolean isServesSingles() {
		return servesSingles;
	}
	public void setServesSingles(boolean servesSingles) {
		this.servesSingles = servesSingles;
	}
		
	public void populateValues(BaseClient client){
		if(client.getVeteranStatus()!=null)
			this.setVeteranStatus(Integer.parseInt(client.getVeteranStatus()));
		if(client.getGender()!=null)
			this.setGender(client.getGender());
		if(client.getDob()!=null)
			this.setAge(DateUtil.calculateAge(client.getDob()));
	}
	
	public void populateValues(EligibleClient client){
		if(client.getSpdatLabel()!=null) {
			if(client.getSpdatLabel().equalsIgnoreCase("TAY") || client.getSpdatLabel().equalsIgnoreCase("SINGLE_AUDLT")){
				this.setServesSingles(true);
			}else{
				this.setServesSingles(false);
			}
		}
	}
}