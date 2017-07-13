package com.hserv.coordinatedentry.housinginventory.service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAddress;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAddressRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.core.model.Session;

@Component
public class HousingInventoryService  {
	
	@Autowired
	private HousingInventoryRepository housingInventoryRepository;
	
	@Autowired
	private HousingUnitAddressRepository HousingUnitAddressRepository;

	/*@Inject
    private HousingInventoryRepository housingInventoryRepository;*/
	
	
//	 @Autowired
//	 HibernateTemplate hibernateTemplate;
	
	 
	 @Transactional
	 public HousingInventory saveHousingInventory(HousingInventory housingInventory,Session  session) {
		    HousingUnitAddress address=housingInventory.getHousingUnitAddress();
		    housingInventory.setDateCreated(LocalDateTime.now());
		    housingInventory.setDateUpdated(LocalDateTime.now());
		    housingInventory.setUserId(session.getAccount().getAccountId());
			housingInventory=housingInventoryRepository.save(housingInventory);
			if(address!=null){
				address.setDateCreated(LocalDateTime.now());
			    address.setDateUpdated(LocalDateTime.now());
			    address.setUserId(session.getAccount().getAccountId());
				address.setHousingInventory(housingInventory);
				HousingUnitAddressRepository.save(address);
			}
		return housingInventory;
	}
	
	 
	 @Transactional
	 public List<HousingInventory> saveHousingInventories(List<HousingInventory> housingInventories,Session session) {
          for(HousingInventory housingInventory: housingInventories){ 
		    HousingUnitAddress address= housingInventory.getHousingUnitAddress();
		    housingInventory= housingInventoryRepository.save(housingInventory);
			if(address!=null){
			address.setAddressId(UUID.randomUUID());
			address.setDateCreated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			address.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			address.setUserId(session.getAccount().getAccountId());
            address.setHousingInventory(housingInventory);
            HousingUnitAddressRepository.save(address);
          }
          }
          return housingInventories;
	}

	
	@Transactional
	public List<HousingInventory> updateHousingInentories(List<HousingInventory> housingInventories,Session session) {
			for(HousingInventory housingInventory : housingInventories){
				HousingInventory unit = housingInventoryRepository.findOne(housingInventory.getHousingInventoryId());
				BeanUtils.copyProperties(housingInventory, unit, "dateCreated");
				unit.setDateUpdated((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				unit.setUserId(session.getAccount().getAccountId());
				housingInventoryRepository.save(unit);				
			}
		return housingInventories;
	}
	
	public Page<HousingInventory> getAllHousingInventory(HousingInventory housingInventory,Pageable pageable){
		Specification<HousingInventory> specification = null;
		
		Specification<HousingInventory> projectIdSpec = Specifications.where(new Specification<HousingInventory>() {

			@Override
			public Predicate toPredicate(Root<HousingInventory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.and(cb.equal(root.get("projectId"), housingInventory.getProjectId()));
			}
			
		});
		
		Specification<HousingInventory> projectGroupCodeSpec = Specifications.where(new Specification<HousingInventory>() {

			@Override
			public Predicate toPredicate(Root<HousingInventory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.and(cb.equal(root.get("projectGroupCode"), housingInventory.getProjectGroupCode()));
			}
			
		});

		Specification<HousingInventory> userIdSpec = Specifications.where(new Specification<HousingInventory>() {

			@Override
			public Predicate toPredicate(Root<HousingInventory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.and(cb.equal(root.get("createdBy"), housingInventory.getUserId()));
			}
			
		});
		
		Specification<HousingInventory> vacantSpec = Specifications.where(new Specification<HousingInventory>() {

			@Override
			public Predicate toPredicate(Root<HousingInventory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.and(cb.equal(root.get("vacant"), housingInventory.getVacant()));
			}
			
		});
		
		
		if(housingInventory.getProjectId()!=null) specification = Specifications.where(specification).and(projectIdSpec);
		if(housingInventory.getUserId()!=null) specification = Specifications.where(specification).and(userIdSpec);
		if(housingInventory.getVacant()!=null) specification = Specifications.where(specification).and(vacantSpec);
		specification = Specifications.where(specification).and(projectGroupCodeSpec);
		return housingInventoryRepository.findAll(specification,pageable);
	}
	
	public Page<HousingInventory> findAll(Pageable pageable){
		return housingInventoryRepository.findAll(pageable);
	}

	public HousingInventory getHousingInventoryById(UUID housingInventoryId) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingInventory inventory =housingInventoryRepository.findByHousingInventoryIdAndProjectGroupCodeAndDeleted(housingInventoryId,projectGroup,false);
		if(inventory==null) throw new ResourceNotFoundException("Housing unit not found "+housingInventoryId);
		return inventory;
	}
	
	public void delete(UUID id) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingInventory inventory =housingInventoryRepository.findByHousingInventoryIdAndProjectGroupCodeAndDeleted(id,projectGroup,false);
		if(inventory==null) throw new ResourceNotFoundException("Housing unit not found "+id);
        housingInventoryRepository.delete(inventory);
    }
	
	
	
}
