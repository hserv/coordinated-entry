package com.hserv.coordinatedentry.housinginventory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAddressRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.SecurityContextUtil;

@Component
public class HousingUnitAddressService  {
	
	@Autowired
	private HousingUnitAddressRepository housingUnitAddressRepository;

	@Autowired
	private HousingInventoryRepository housingInventoryRepository;
	

	 public HousingUnitAddress saveHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
		 
			String projectGroup = SecurityContextUtil.getUserProjectGroup();
		 HousingInventory housingInventory = housingInventoryRepository.findByHousingInventoryIdAndProjectGroupCodeAndDeleted(housingUnitAddress.getHousingInventory().getHousingInventoryId(),projectGroup,false);
		 
		 if(housingInventory==null) throw new ResourceNotFoundException("Housing unit not found "+housingUnitAddress.getHousingInventory().getHousingInventoryId());
		 
		 housingUnitAddress.setHousingInventory(housingInventory);
		 housingUnitAddressRepository.save(housingUnitAddress);
		
		return housingUnitAddress;
	}

	public HousingUnitAddress updateHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		 HousingUnitAddress add = housingUnitAddressRepository.findByAddressIdAndProjectGroupCodeAndDeleted(housingUnitAddress.getAddressId(),projectGroup,false);
		 if(add!=null) BeanUtils.copyProperties(housingUnitAddress, add,"dateCreated");
		return 	housingUnitAddressRepository.save(add);
		
	}
	
	public List<HousingUnitAddress> findAll(){
		return housingUnitAddressRepository.findAll();
	}
	
	public Page<HousingUnitAddress> getAllHousingUnitAddress(UUID housingUnitId,Pageable pageable){
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingInventory housingInventory=housingInventoryRepository.findByHousingInventoryIdAndProjectGroupCodeAndDeleted(housingUnitId,projectGroup,false);
		return housingUnitAddressRepository.findByHousingInventoryAndDeleted(housingInventory,false,pageable);
	}

	public HousingUnitAddress getHousingUnitAddressById(UUID id) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingUnitAddress address = housingUnitAddressRepository.findByAddressIdAndProjectGroupCodeAndDeleted(id,projectGroup,false);
		if(address==null) throw new ResourceNotFoundException("Housing unit address not found "+id);
		return housingUnitAddressRepository.findOne(id);
	}
	
	public void delete(UUID id) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingUnitAddress address = housingUnitAddressRepository.findByAddressIdAndProjectGroupCodeAndDeleted(id,projectGroup,false);
		if(address==null) throw new ResourceNotFoundException("Housing unit address not found "+id);
        housingUnitAddressRepository.delete(address);
    }
	
	//this mehtod ll return false if the housingunit address is not equals to previous one.
	public boolean housingUnitAddressEqualsExistingAddress(HousingUnitAddress existingAddress, HousingUnitAddress updatedAddress){
		if(existingAddress.getCity().equals(updatedAddress.getCity())
				&&existingAddress.getLine1().equals(updatedAddress.getLine1())
				&&existingAddress.getLine2().equals(updatedAddress.getLine2())
			    &&existingAddress.getState().equals(updatedAddress.getState())
			    &&existingAddress.getZipCode().equals(updatedAddress.getZipCode()))
		return true;
		else 
			return false;
	}
}
