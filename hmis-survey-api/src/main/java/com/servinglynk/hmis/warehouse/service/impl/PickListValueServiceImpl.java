package com.servinglynk.hmis.warehouse.service.impl; 

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.core.model.PickListValue;
import com.servinglynk.hmis.warehouse.core.model.PickListValues;
import com.servinglynk.hmis.warehouse.core.model.SortedPagination;
import com.servinglynk.hmis.warehouse.model.PickListGroupEntity;
import com.servinglynk.hmis.warehouse.model.PickListValueEntity;
import com.servinglynk.hmis.warehouse.service.PickListValueService;
import com.servinglynk.hmis.warehouse.service.converter.PickListValueConverter;
import com.servinglynk.hmis.warehouse.service.exception.PickListGroupNotFoundException;
import com.servinglynk.hmis.warehouse.service.exception.PickListValueNotFoundException;


@Component
public class PickListValueServiceImpl extends ServiceBase implements PickListValueService  {

   @Transactional
   public PickListValues createPickListValue(UUID pickListGroupId,PickListValues pickListValues,String caller){
	  PickListValues returnPickListValues = new PickListValues();
	   
	   PickListGroupEntity groupEntity = daoFactory.getPickListGroupEntityDao().getPickListGroupEntityById(pickListGroupId);
	   if(groupEntity==null) throw new PickListGroupNotFoundException();
      
	   for(PickListValue pickListValue : pickListValues.getPickListValues()){
	   
		   PickListValueEntity pPickListValue = PickListValueConverter.modelToEntity(pickListValue, null);
		   pPickListValue.setPickListGroupEntity(groupEntity);
		   pPickListValue.setCreatedAt(LocalDateTime.now());
		   pPickListValue.setUser(caller);
		   daoFactory.getPickListValueEntityDao().createPickListValueEntity(pPickListValue);
		   returnPickListValues.addPickListValue(new PickListValue(pPickListValue.getId()));
	   }
       return returnPickListValues;
   }


   @Transactional
   public PickListValue updatePickListValue(PickListValue PickListValue,String caller){
       PickListValueEntity pPickListValue = daoFactory.getPickListValueEntityDao().getPickListValueEntityById(PickListValue.getPickListValueId());
       if(pPickListValue==null) throw new PickListValueNotFoundException();

       PickListValueConverter.modelToEntity(PickListValue, pPickListValue);
       pPickListValue.setUpdatedAt(LocalDateTime.now());
       pPickListValue.setUser(caller);
       daoFactory.getPickListValueEntityDao().updatePickListValueEntity(pPickListValue);
       PickListValue.setPickListValueId(pPickListValue.getId());
       return PickListValue;
   }


   @Transactional
   public PickListValue deletePickListValue(UUID PickListValueId,String caller){
       PickListValueEntity pPickListValue = daoFactory.getPickListValueEntityDao().getPickListValueEntityById(PickListValueId);
       if(pPickListValue==null) throw new PickListValueNotFoundException();

       pPickListValue.setUser(caller);
       daoFactory.getPickListValueEntityDao().deletePickListValueEntity(pPickListValue);
       return new PickListValue();
   }


   @Transactional
   public PickListValue getPickListValueById(UUID PickListValueId){
       PickListValueEntity pPickListValue = daoFactory.getPickListValueEntityDao().getPickListValueEntityById(PickListValueId);
       if(pPickListValue==null) throw new PickListValueNotFoundException();

       return PickListValueConverter.entityToModel( pPickListValue );
   }


   @Transactional
   public PickListValues getAllPickListGroupPickListValues(UUID pickListGroupId,Integer startIndex, Integer maxItems){
       PickListValues PickListValues = new PickListValues();
        List<PickListValueEntity> entities = daoFactory.getPickListValueEntityDao().getAllPickListGroupPickListValueEntities(pickListGroupId,startIndex,maxItems);
        for(PickListValueEntity entity : entities){
           PickListValues.addPickListValue(PickListValueConverter.entityToModel(entity));
        }
        long count = daoFactory.getPickListValueEntityDao().getPickListGroupPickListValueEntitiesCount(pickListGroupId);
        SortedPagination pagination = new SortedPagination();
 
        pagination.setFrom(startIndex);
        pagination.setReturned(PickListValues.getPickListValues().size());
        pagination.setTotal((int)count);
        PickListValues.setPagination(pagination);
        return PickListValues; 
   }
}
