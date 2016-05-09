package com.hserv.coordinatedentry.housinginventory.service;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAddressRepository;

@Component
public class HousingInventoryService  {
	
	@Autowired
	private HousingInventoryRepository housingInventoryRepository;
	
	@Autowired
	private HousingUnitAddressRepository HousingUnitAddressRepository;

	/*@Inject
    private HousingInventoryRepository housingInventoryRepository;*/
	
	
	 @Autowired
	 HibernateTemplate hibernateTemplate;
	
	 @Transactional
	 public HousingInventory saveHousingInventory(HousingInventory housingInventory) {
			housingInventory.setHousingInventoryId(UUID.randomUUID());
			housingInventory.setInactive(false);
			housingInventory.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			housingInventory.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		    HousingUnitAddress address=housingInventory.getHousingUnitAddress();
		  /*  Set<HousingUnitAssignment> assignments=new HashSet<HousingUnitAssignment>();
		    for(HousingUnitAssignment assignment: housingInventory.getHousingUnitAssignments()){
		    	assignment.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		    	assignment.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		    	assignment.setAssignmentId(UUID.randomUUID());
		    	assignments.add(assignment);
		    }
		    housingInventory.setHousingUnitAssignments(null);
		    housingInventory.setHousingUnitAssignments(assignments);*/
			housingInventory=housingInventoryRepository.save(housingInventory);
			if(address!=null){
				address.setAddressId(UUID.randomUUID());
				address.setInactive(false);
				address.setHousingInventory(housingInventory);
				address.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				address.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				HousingUnitAddressRepository.save(address);
			}
			//housingInventory.setHousingUnitAddress(address);
		return housingInventory;
	}
	
	 
	 @Transactional
	 public List<HousingInventory> saveHousingInventories(List<HousingInventory> housingInventories) {
          for(HousingInventory housingInventory: housingInventories){ 
		    housingInventory.setHousingInventoryId(UUID.randomUUID());
		    housingInventory.setInactive(false);
			housingInventory.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			housingInventory.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		    HousingUnitAddress address= housingInventory.getHousingUnitAddress();
		    housingInventory= housingInventoryRepository.save(housingInventory);
			if(address!=null){
			address.setAddressId(UUID.randomUUID());
			address.setInactive(false);
			//address.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			//address.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            address.setHousingInventory(housingInventory);
            HousingUnitAddressRepository.save(address);
            //housingInventory.setHousingUnitAddress(address);
          }
          }
          return housingInventories;
	}

	
	@Transactional
	public List<HousingInventory> updateHousingInentories(List<HousingInventory> housingInventories) {
			housingInventoryRepository.save(housingInventories);
		return housingInventories;
	}
	
	@SuppressWarnings("unchecked")
	public List<HousingInventory> getAllHousingInventory(HousingInventory housingInventory){
		Example filter=Example.create(housingInventory);
		DetachedCriteria crit=DetachedCriteria.forClass(HousingInventory.class).add(filter);
		List<HousingInventory> housingInventories=(List<HousingInventory>)hibernateTemplate.findByCriteria(crit);
		return  housingInventories;//housingInventoryRepository.findAll();
	}

	@SuppressWarnings("unchecked")
	public HousingInventory getHousingInventoryById(UUID housingInventoryId) {
		List<HousingInventory> list=new ArrayList<HousingInventory>(0);
		DetachedCriteria crit=DetachedCriteria.forClass(HousingInventory.class);
		crit.add(Restrictions.eq("housingInventoryId", housingInventoryId));
		crit.setFetchMode("address", FetchMode.JOIN);
		list=(List<HousingInventory>)hibernateTemplate.findByCriteria(crit);
		if(list.size()!=0)
			return list.get(0);
		else 
		return null;
		//return housingInventoryRepository.findOne(housingInventoryId);
	}
	
	public void delete(UUID id) {
        //log.debug("Request to delete HousingInventory : {}", id);
        housingInventoryRepository.delete(id);
    }
	
}
