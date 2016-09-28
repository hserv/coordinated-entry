package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.hserv.coordinatedentry.housingmatching.entity.HousingInventory;
import com.hserv.coordinatedentry.housingmatching.service.ServiceFactory;

public class HousingUnitServiceImpl {
	
	@Autowired
	ServiceFactory serviceFactory;
	
	
	public List<HousingInventory> getHousingUnits(UUID projectId,boolean isFamily,Integer members){
		Object[] capacity = new Integer[]{members,members-1};
		
		Specification<HousingInventory> spec = Specifications.where(new Specification<HousingInventory>() {

			@Override
			public Predicate toPredicate(Root<HousingInventory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
   
				return criteriaBuilder.and(criteriaBuilder.equal(root.get("projectId"),projectId),
						criteriaBuilder.equal(root.get("familyUnit"), isFamily),
						criteriaBuilder.upper(root.get("bedsCapacity")).in(capacity));				
			}
		});
		
	  return serviceFactory.getRepositoryFactory().getHousingUnitsRepository().findAll(spec);
		
	}

}
