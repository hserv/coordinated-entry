package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import app.entity.Survey;

@Component
public interface SurveyRepository extends JpaRepository<Survey, Integer>{

	
	public List<Survey> findBySurveyTitleContainingAllIgnoringCase(String title) ;
	
}
