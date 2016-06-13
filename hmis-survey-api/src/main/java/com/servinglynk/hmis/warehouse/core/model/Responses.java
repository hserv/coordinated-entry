package com.servinglynk.hmis.warehouse.core.model; 

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("responses")
public class Responses extends PaginatedModel{


       @JsonProperty("responses") 
       List<Response>responses = new ArrayList<Response>();
       public List<Response> getResponses() {
           return responses;
       }

        public void setResponses(List<Response> responses) {
           this.responses = responses;
       }
 
       public void addResponse(Response response) {
           this.responses.add(response);
       }
 }
