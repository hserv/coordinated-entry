package model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.PaginatedModel;
import com.servinglynk.hmis.warehouse.core.model.Survey;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("ArrayList")
public class ClientSurveySubmissions {
	
	@JsonProperty("ArrayList")
	List<ClientSurveySubmission> list = new ArrayList<ClientSurveySubmission>();

	public List<ClientSurveySubmission> getList() {
		return list;
	}

	public void setList(List<ClientSurveySubmission> list) {
		this.list = list;
	}

	
}


//@JsonRootName("surveys")
//public class Surveys extends PaginatedModel{
//
//
//       @JsonProperty("surveys") 
//       List<Survey> survies = new ArrayList<Survey>();
//       public List<Survey> getSurvies() {
//           return survies;
//       }
//
//        public void setSurvies(List<Survey> survies) {
//           this.survies = survies;
//       }
// 
//       public void addSurvey(Survey survey) {
//           this.survies.add(survey);
//       }
// }