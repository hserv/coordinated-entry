package com.hserv.coordinatedentry.housinginventory.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hserv.coordinatedentry.housinginventory.domain.HousingInventory;
import com.hserv.coordinatedentry.housinginventory.domain.Project;

public interface ProjectRepository  extends JpaRepository<Project,
Serializable> , JpaSpecificationExecutor<Project>{

}
