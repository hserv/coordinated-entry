package com.hserv.coordinatedentry.housinginventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAddressRepository;

@Component
public class HousingUnitAddressService  {
	
	@Autowired
	private HousingUnitAddressRepository housingUnitAddressRepository;

	@Autowired
	private HousingInventoryRepository housingInventoryRepository;
	
	
//	 @Autowired
//	 HibernateTemplate hibernateTemplate;
	
	 public HousingUnitAddress saveHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
/*		 DetachedCriteria crit=DetachedCriteria.forClass(HousingUnitAddress.class)
				 .add(Restrictions.eq("housingInventory.housingInventoryId",housingUnitAddress.getHousingInventory().getHousingInventoryId()))
				 .addOrder(Order.asc("dateUpdated"));
		 List<HousingUnitAddress> addresses= (List<HousingUnitAddress>)hibernateTemplate.findByCriteria(crit);*/
		 
		 HousingInventory housingInventory = housingInventoryRepository.findOne(housingUnitAddress.getHousingInventory().getHousingInventoryId());
		 List<HousingUnitAddress> addresses = housingUnitAddressRepository.findByHousingInventory(housingInventory);
		 
		 if(addresses!=null&&addresses.size()!=0)
		 {
			 HousingUnitAddress address=addresses.get(0);
			 if(!(housingUnitAddressEqualsExistingAddress(address,housingUnitAddress))){
				 housingUnitAddress=housingUnitAddressRepository.save(housingUnitAddress);
			 }
		 }else{
			 housingUnitAddress=housingUnitAddressRepository.save(housingUnitAddress);
		 }
		return housingUnitAddress;
	}

	public HousingUnitAddress updateHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
		return 	housingUnitAddressRepository.save(housingUnitAddress);
		
	}
	
	public List<HousingUnitAddress> findAll(){
		return housingUnitAddressRepository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public Page<HousingUnitAddress> getAllHousingUnitAddress(UUID housingUnitId,Pageable pageable){
		HousingInventory housingInventory=housingInventoryRepository.findOne(housingUnitId);
		return housingUnitAddressRepository.findByHousingInventory(housingInventory,pageable);
	}

	@SuppressWarnings("unchecked")
	public HousingUnitAddress getHousingUnitAddressById(UUID id) {
		return housingUnitAddressRepository.findOne(id);
	}
	
	public void delete(UUID id) {
		HousingUnitAddress housingUnitAddress = housingUnitAddressRepository.findOne(id);
        housingUnitAddressRepository.delete(housingUnitAddress);
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
