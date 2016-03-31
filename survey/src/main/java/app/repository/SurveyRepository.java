package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.model.Survey;

@Component
public interface SurveyRepository extends JpaRepository<Survey, Integer>{

}
