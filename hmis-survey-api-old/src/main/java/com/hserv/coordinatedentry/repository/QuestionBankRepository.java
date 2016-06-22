package com.hserv.coordinatedentry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hserv.coordinatedentry.entity.Question;

public interface QuestionBankRepository extends JpaRepository<Question, Integer>{


}
