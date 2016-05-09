package com.hserv.coordinatedentry.housinginventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	 @Autowired
	 HibernateTemplate hibernateTemplate;
	
	 public HousingUnitAddress saveHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
			housingUnitAddress.setAddressId(UUID.randomUUID());
			housingUnitAddress=housingUnitAddressRepository.save(housingUnitAddress);
		return housingUnitAddress;
	}

	public boolean updateHousingUnitAddress(HousingUnitAddress housingUnitAddress) {
		boolean flag=false;
		try {
			housingUnitAddressRepository.save(housingUnitAddress);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public List<HousingUnitAddress> findAll(){
		return housingUnitAddressRepository.findAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<HousingUnitAddress> getAllHousingUnitAddress(UUID housingUnitId){
		List<HousingUnitAddress> housingUnitAddress=new ArrayList<HousingUnitAddress>(0);
		HousingInventory housingInventory=housingInventoryRepository.findOne(housingUnitId);
		for(HousingUnitAddress addr: housingInventory.getHousingUnitAddresss()){
			addr.setHousingInventory(null);
			addr.setHousingInventoryId(housingUnitId.toString());
			housingUnitAddress.add(addr);
		}
	
		//housingUnitAddressRepository.findAll();
		/*DetachedCriteria crit=DetachedCriteria.forClass(HousingUnitAddress.class)
				.createCriteria("housingInventory")
				.add(Restrictions.eq("housingInventoryId", housingUnitId));
		//crit.add(Restrictions.eq("housingInventory.housingInventoryId","housingUnitId", FetchMode.JOIN));
		List<HousingUnitAddress> housingUnitAddress=(List<HousingUnitAddress>)hibernateTemplate.findByCriteria(crit);*/
		return housingUnitAddress;
	}

	@SuppressWarnings("unchecked")
	public HousingUnitAddress getHousingUnitAddressById(UUID id) {
		return housingUnitAddressRepository.findOne(id);
	}
	
	public void delete(UUID id) {
        //log.debug("Request to delete HousingUnitAddress : {}", id);
        housingUnitAddressRepository.delete(id);
    }
}
