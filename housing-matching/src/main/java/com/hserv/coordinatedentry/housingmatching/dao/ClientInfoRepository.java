package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hserv.coordinatedentry.housingmatching.entity.ClientInfo;

//@Repository

public interface ClientInfoRepository extends CrudRepository<ClientInfo, Serializable>{

}
