package com.hserv.coordinatedentry.housinginventory.service;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.HousingUnitAssignment;
import com.hserv.coordinatedentry.housinginventory.repository.HousingInventoryRepository;
import com.hserv.coordinatedentry.housinginventory.repository.HousingUnitAssignmentRepository;
import com.hserv.coordinatedentry.housinginventory.web.rest.util.SecurityContextUtil;

@Component
public class HousingUnitAssignmentService  {
	
	@Autowired
	private HousingUnitAssignmentRepository housingUnitAssignmentRepository;
	
	@Autowired
	HousingInventoryRepository HousingInventoryRepository;
	
	 @Transactional
	 public List<HousingUnitAssignment> saveHousingUnitAssignments(List<HousingUnitAssignment> housingUnitAssignments, UUID housingUnitId) {
		 
		 HousingInventory housingInventory = HousingInventoryRepository.findOne(housingUnitId);
		 
		 for(HousingUnitAssignment housingUnitAssignment: housingUnitAssignments ){
			 housingUnitAssignment.setHousingInventory(housingInventory);
			housingUnitAssignment=housingUnitAssignmentRepository.save(housingUnitAssignment);
		 }
		return housingUnitAssignments;
	}
	 
	 @Transactional
	 public HousingUnitAssignment saveHousingUnitAssignment(HousingUnitAssignment housingUnitAssignments) {
		 housingUnitAssignments=housingUnitAssignmentRepository.save(housingUnitAssignments);
		return housingUnitAssignments;
	}

	public List<HousingUnitAssignment> updateHousingUnitAssignments(List<HousingUnitAssignment> housingUnitAssignments) {
		for(HousingUnitAssignment assignment : housingUnitAssignments){
			HousingUnitAssignment unitAssignment = housingUnitAssignmentRepository.findOne(assignment.getAssignmentId());
			if(unitAssignment!=null){ 
				BeanUtils.copyProperties(assignment, unitAssignment,"dateCreated");
				housingUnitAssignmentRepository.save(unitAssignment);
			}else{
				housingUnitAssignmentRepository.save(assignment);
			}
		}		return housingUnitAssignments;
	}
	
	public Page<HousingUnitAssignment> getAllHousingUnitAssignments(UUID housingUnitId,Pageable pageable){
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingInventory housingInventory= HousingInventoryRepository.findByHousingInventoryIdAndProjectGroupCode(housingUnitId, projectGroup);
		if(housingInventory==null) throw new ResourceNotFoundException("Housing unit not found "+housingUnitId);
		return housingUnitAssignmentRepository.findByHousingInventory(housingInventory, pageable);
	}

	public HousingUnitAssignment getHousingUnitAssignmentById(UUID id) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingUnitAssignment assignment = housingUnitAssignmentRepository.findByAssignmentIdAndProjectGroupCode(id,projectGroup);
		if(assignment==null) throw new ResourceNotFoundException("Housing assignment not found "+id);
		return assignment; 
	}
	
	public void delete(UUID id) {
		String projectGroup = SecurityContextUtil.getUserProjectGroup();
		HousingUnitAssignment assignment = housingUnitAssignmentRepository.findByAssignmentIdAndProjectGroupCode(id,projectGroup);
		if(assignment==null) throw new ResourceNotFoundException("Housing assignment not found "+id);
        housingUnitAssignmentRepository.delete(assignment);
    }
}
