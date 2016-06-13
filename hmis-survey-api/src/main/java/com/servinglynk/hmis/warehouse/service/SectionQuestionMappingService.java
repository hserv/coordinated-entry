package com.servinglynk.hmis.warehouse.service; 

import java.util.UUID;

import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMapping;
import com.servinglynk.hmis.warehouse.core.model.SectionQuestionMappings;
public interface SectionQuestionMappingService {

   void createSectionQuestionMapping(UUID surveysectionid, SectionQuestionMappings questions,String caller);
   SectionQuestionMapping deleteSectionQuestionMapping(UUID SectionQuestionMappingId,String caller);
   SectionQuestionMappings getAllSectionQuestionMappings(UUID surveysectionid, Integer startIndex, Integer maxItems);
}
