package com.hserv.coordinatedentry.housinginventory.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAssignmentRepository;

@Component
public class HousingUnitAssignmentService  {
	
	@Autowired
	private HousingUnitAssignmentRepository housingUnitAssignmentRepository;
	
	@Autowired
	HousingInventoryRepository HousingInventoryRepository;
	
	 @Autowired
	 HibernateTemplate hibernateTemplate;
	
	 @Transactional
	 public List<HousingUnitAssignment> saveHousingUnitAssignments(List<HousingUnitAssignment> housingUnitAssignments) {
		 for(HousingUnitAssignment housingUnitAssignment: housingUnitAssignments ){
			housingUnitAssignment.setAssignmentId(UUID.randomUUID());
			housingUnitAssignment=housingUnitAssignmentRepository.save(housingUnitAssignment);
		 }
		return housingUnitAssignments;
	}
	 
	 @Transactional
	 public HousingUnitAssignment saveHousingUnitAssignment(HousingUnitAssignment housingUnitAssignments) {
		 //for(HousingUnitAssignment housingUnitAssignment: housingUnitAssignments ){
		 housingUnitAssignments.setAssignmentId(UUID.randomUUID());
		 housingUnitAssignments=housingUnitAssignmentRepository.save(housingUnitAssignments);
		 //}
		return housingUnitAssignments;
	}

	public List<HousingUnitAssignment> updateHousingUnitAssignments(List<HousingUnitAssignment> housingUnitAssignments) {
			housingUnitAssignmentRepository.save(housingUnitAssignments);
		return housingUnitAssignments;
	}
	
	@SuppressWarnings("unchecked")
	public List<HousingUnitAssignment> getAllHousingUnitAssignments(UUID housingUnitId){
		/*DetachedCriteria crit=DetachedCriteria.forClass(HousingUnitAssignment.class);
		//crit.setFetchMode("housingInventory", org.hibernate.FetchMode.JOIN);
		//crit.add(Restrictions.eq("housingInventory.housingInventoryId", housingUnitId));
		crit.createAlias("housingInventory", "housingInventory");
		crit.add(Restrictions.eq("housingInventory.housingInventoryId",housingUnitId));
		List<HousingUnitAssignment> housingInventoryies=(List<HousingUnitAssignment>)hibernateTemplate.findByCriteria(crit);
		return housingInventoryies; //housingUnitAssignmentRepository.findAll();
        */
		List<HousingUnitAssignment> assignments=new ArrayList<HousingUnitAssignment>(0);
		HousingInventory housingInventory= HousingInventoryRepository.findOne(housingUnitId);
		for(HousingUnitAssignment assignment: housingInventory.getHousingUnitAssignments()){
			assignment.setHousingInventory(null);
			assignment.setHousingInventoryId(housingUnitId.toString());
			assignments.add(assignment);
		}
	    return assignments;	
	}

	@SuppressWarnings("unchecked")
	public HousingUnitAssignment getHousingUnitAssignmentById(UUID id) {
		/*List<HousingUnitAssignment> list=new ArrayList<HousingUnitAssignment>(0);
		DetachedCriteria crit=DetachedCriteria.forClass(HousingUnitAssignment.class);
		crit.add(Restrictions.eq("housingInventoryId", housingInventoryId));
		//crit.setFetchMode("address", FetchMode.JOIN);
		list=(List<HousingUnitAssignment>)hibernateTemplate.findByCriteria(crit);
		if(list.size()!=0)
			return list.get(0);
		else
		return null;*/
		return housingUnitAssignmentRepository.findOne(id);
	}
	
	public void delete(UUID id) {
        //log.debug("Request to delete HousingUnitAssignment : {}", id);
        housingUnitAssignmentRepository.delete(id);
    }
}
