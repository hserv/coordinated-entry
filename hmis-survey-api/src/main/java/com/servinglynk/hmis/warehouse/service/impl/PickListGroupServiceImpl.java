package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.PickListGroup;
import com.servinglynk.hmis.warehouse.core.model.PickListGroups;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;
import com.servinglynk.hmis.warehouse.service.PickListGroupService;
import com.servinglynk.hmis.warehouse.service.converter.PickListGroupConverter;
import com.servinglynk.hmis.warehouse.service.exception.PickListGroupNotFoundException;


@Component
public class PickListGroupServiceImpl extends ServiceBase implements PickListGroupService  {

   @Transactional
   public PickListGroup createPickListGroup(PickListGroup PickListGroup,String caller){
       PickListGroupEntity pPickListGroup = PickListGroupConverter.modelToEntity(PickListGroup, null);
       pPickListGroup.setCreatedAt(LocalDateTime.now());
       pPickListGroup.setUser(getUser());
       daoFactory.getPickListGroupEntityDao().createPickListGroupEntity(pPickListGroup);
       PickListGroup.setPickListGroupId(pPickListGroup.getId());
       return PickListGroup;
   }


   @Transactional
   public PickListGroup updatePickListGroup(PickListGroup PickListGroup,String caller){
       PickListGroupEntity pPickListGroup = daoFactory.getPickListGroupEntityDao().getPickListGroupEntityById(PickListGroup.getPickListGroupId());
       if(pPickListGroup==null) throw new PickListGroupNotFoundException();

       PickListGroupConverter.modelToEntity(PickListGroup, pPickListGroup);
       pPickListGroup.setUpdatedAt(LocalDateTime.now());
       pPickListGroup.setUser(getUser());
       daoFactory.getPickListGroupEntityDao().updatePickListGroupEntity(pPickListGroup);
       PickListGroup.setPickListGroupId(pPickListGroup.getId());
       return PickListGroup;
   }


   @Transactional
   public PickListGroup deletePickListGroup(UUID PickListGroupId,String caller){
       PickListGroupEntity pPickListGroup = daoFactory.getPickListGroupEntityDao().getPickListGroupEntityById(PickListGroupId);
       if(pPickListGroup==null) throw new PickListGroupNotFoundException();

       pPickListGroup.setUser(getUser());
       daoFactory.getPickListGroupEntityDao().deletePickListGroupEntity(pPickListGroup);
       return new PickListGroup();
   }


   @Transactional
   public PickListGroup getPickListGroupById(UUID PickListGroupId){
       PickListGroupEntity pPickListGroup = daoFactory.getPickListGroupEntityDao().getPickListGroupEntityById(PickListGroupId);
       if(pPickListGroup==null) throw new PickListGroupNotFoundException();

       return PickListGroupConverter.entityToModel( pPickListGroup );
   }


   @Transactional
   public PickListGroups getAllPickListGroups(Integer startIndex, Integer maxItems){
       PickListGroups PickListGroups = new PickListGroups();
        List<PickListGroupEntity> entities = daoFactory.getPickListGroupEntityDao().getAllPickListGroupEntitys(startIndex,maxItems);
        for(PickListGroupEntity entity : entities){
           PickListGroups.addPickListGroup(PickListGroupConverter.entityToModel(entity));
        }
        long count = daoFactory.getPickListGroupEntityDao().getPickListGroupEntitysCount();
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(PickListGroups.getpickListGroups().size());
        pagination.setTotal((int)count);
        PickListGroups.setPagination(pagination);
        return PickListGroups; 
   }
}