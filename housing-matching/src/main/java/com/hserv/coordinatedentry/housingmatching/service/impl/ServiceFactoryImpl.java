package com.hserv.coordinatedentry.housingmatching.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.dao.RepositoryFactory;
import com.hserv.coordinatedentry.housingmatching.service.ServiceFactory;

@Component
public class ServiceFactoryImpl implements ServiceFactory {

	@Autowired
	RepositoryFactory repositoryFactory;

	public RepositoryFactory getRepositoryFactory() {
		return repositoryFactory;
	}

	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		this.repositoryFactory = repositoryFactory;
	}
	
	
}
