package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Question;

public interface QuestionBankRepository extends JpaRepository<Question, Integer>{


}
