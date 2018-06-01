package com.hserv.coordinatedentry.housinginventory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housinginventory.domain.GlobalProjectMapEntity;
import com.hserv.coordinatedentry.housinginventory.repository.GlobalProjectMapRepository;

@Component
public class ProjectService {

	@Autowired
	GlobalProjectMapRepository globalProjectMapRepository;
	
	public String populateProjectSchemaYear(UUID projectId,String projectGroupCode) throws Exception {
		List<GlobalProjectMapEntity> projectMaps =	globalProjectMapRepository.findByProjectIdAndProjectGroupCode(projectId, projectGroupCode);
		if(projectMaps.isEmpty()) {
			return null;
		}else {
			return projectMaps.get(0).getSource();
		}
	}
}