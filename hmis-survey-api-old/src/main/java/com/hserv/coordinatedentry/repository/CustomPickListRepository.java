package com.hserv.coordinatedentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.entity.CustomPicklist;

public interface CustomPickListRepository extends JpaRepository<CustomPicklist, Integer>{

}
