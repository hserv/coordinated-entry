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
		 housingUnitAssignments.setAssignmentId(UUID.randomUUID());
		 housingUnitAssignments=housingUnitAssignmentRepository.save(housingUnitAssignments);
		return housingUnitAssignments;
	}

	public List<HousingUnitAssignment> updateHousingUnitAssignments(List<HousingUnitAssignment> housingUnitAssignments) {
			housingUnitAssignmentRepository.save(housingUnitAssignments);
		return housingUnitAssignments;
	}
	
	@SuppressWarnings("unchecked")
	public List<HousingUnitAssignment> getAllHousingUnitAssignments(UUID housingUnitId){
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
		return housingUnitAssignmentRepository.findOne(id);
	}
	
	public void delete(UUID id) {
		housingUnitAssignmentRepository.findOne(id);
        housingUnitAssignmentRepository.delete(id);
    }
}
