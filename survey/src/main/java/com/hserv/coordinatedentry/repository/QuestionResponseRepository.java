package com.hserv.coordinatedentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.entity.ResponseStorage;

public interface QuestionResponseRepository extends JpaRepository<ResponseStorage, Integer>{

}
