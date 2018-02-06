package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.core.Is.is;
//import com.jayway.jsonpath.JsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servinglynk.hmis.warehouse.core.model.ClientSurveySubmission;
import com.servinglynk.hmis.warehouse.core.model.*;

import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.service.core.ParentServiceFactory;

import configuration.DatabaseConfigTest;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration(classes = DatabaseConfigTest.class),
})




@Transactional
public class ClientSurveySubmissionTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ParentServiceFactory serviceFactory;
		
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
	private ObjectMapper rootMapper;
	
	private MockMvc mockMvc;

	private UUID entityId;
	private UUID globalEnrollmentId;
	private UUID clientId;
	private UUID surveyId;
	private UUID submissionId;
	
    @Before
    public void setup () {
    	
    	
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
        
        List<ClientSurveySubmissionEntity> list = serviceFactory.getClientSurveySubmissionService()
        			.getClientSurveySubmissionEntitybyClientId(UUID.fromString("326c9435-ece2-4850-9f3f-b76d91451510"));
        		
        entityId = list.get(0).getId();
        globalEnrollmentId = UUID.fromString("a5aa6810-b1ef-4e7f-b3a9-994a63c0c52a");
        clientId = list.get(0).getClientId();
        surveyId = list.get(0).getSurveyId();
        submissionId = list.get(0).getSubmissionId();
        logger.info("before done");
    }
    								  

    
    //@Ignore
    @Test
    public void testMethodPutbyId () throws Exception {
    	
    	ClientSurveySubmission css = new ClientSurveySubmission();
    	css.setId(entityId);
    	
				
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		String jsonStr ="";
		Map<String, String> json = new HashMap<String, String>();
		json.put("global_enrollment_id", globalEnrollmentId.toString());

		try {
			jsonStr = rootMapper.writeValueAsString(json);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		logger.info("JSon String: {}", jsonStr);  
        MockHttpServletRequestBuilder builder =
        		MockMvcRequestBuilders.put("/clientsurveysubmission/id/{id}" ,css.getId())
        			.headers(headers)
        			.contentType(MediaType.APPLICATION_JSON)
        			.content(jsonStr);
        MockHttpServletResponse response = this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        
        ClientSurveySubmissionEntity csseu = serviceFactory.getClientSurveySubmissionService().getClientSurveySubmissionEntitybyId(entityId);
        assertEquals(globalEnrollmentId,csseu.getGlobalEnrollmentId());
        assertEquals(200,response.getStatus());
        
    }
   // @Ignore
    @Test
    public void testMethodPutbyClientSurveySubmission () throws Exception {
    ClientSurveySubmission css = new ClientSurveySubmission();
    	
		css.setClientId(clientId);
		css.setSubmissionId(submissionId);
		css.setSurveyId(surveyId);
			
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	
	String jsonStr ="";
	Map<String, String> json = new HashMap<String, String>();
	json.put("global_enrollment_id", globalEnrollmentId.toString());

	try {
		jsonStr = rootMapper.writeValueAsString(json);
    } catch (Exception e) {
    	e.printStackTrace();
    }
	
    MockHttpServletRequestBuilder builder =
    		MockMvcRequestBuilders.put("/clientsurveysubmission/client/{clientId}/survey/{surveyId}/submission/{submissionId}" ,
    				css.getClientId(),css.getSurveyId(),css.getSubmissionId())
    			.headers(headers)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(jsonStr);
    MockHttpServletResponse response = this.mockMvc.perform(builder)
    		.andExpect(MockMvcResultMatchers.status().isOk())
    		//.andExpect(MockMvcResultMatchers.jsonPath($.id,""))
    		.andReturn().getResponse();
    
    ClientSurveySubmissionEntity csseu = serviceFactory.getClientSurveySubmissionService().getClientSurveySubmissionEntitybyId(entityId);
   logger.info("RESULT: {}",csseu.toString());
    assertEquals(globalEnrollmentId,csseu.getGlobalEnrollmentId());
    assertEquals(200,response.getStatus());
   
    
    } 
    //@Ignore
    @Test	
	public void testMethodGetAllbyClientId() throws Exception{
		
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	
    	MockHttpServletRequestBuilder builder =
			MockMvcRequestBuilders.get("/clientsurveysubmission/clientId/{clientId}",clientId)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON);
    	MockHttpServletResponse response = this.mockMvc.perform(builder)
    		.andExpect(MockMvcResultMatchers.status().isOk())
    		.andReturn().getResponse();
    	
    	List<ClientSurveySubmissionEntity> list = rootMapper.readValue(response.getContentAsString(), ArrayList.class);	
    	
       	assertTrue(list.size()>1);
    	assertEquals(200,response.getStatus());
    	
}
    //@Ignore
	@Test
	public void testCreateRecord() throws Exception {
	
		ClientSurveySubmissionEntity csse = new ClientSurveySubmissionEntity();
		csse.setClientId(UUID.randomUUID());
		csse.setSubmissionId(UUID.randomUUID());
		csse.setSurveyId(UUID.randomUUID());
		ClientSurveySubmissionEntity result = serviceFactory.getClientSurveySubmissionService().createClientSurveySubmissionEntity(csse);
				
		logger.info("Provided id:{}",csse.getId().toString());
		logger.info("Stored id:{}",csse.getId().toString());
		
		 
		assertEquals(result.getId(),csse.getId());
	}
    //@Ignore
	@Test
	public void testResourceNotFound() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	
    	MockHttpServletRequestBuilder builder =
			MockMvcRequestBuilders.get("/clientsurveysubmission/notfound")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON);
    	MockHttpServletResponse response = this.mockMvc.perform(builder)
    		.andReturn().getResponse();
    	assertEquals(404,response.getStatus());
	}
	
	@Ignore
	@Test
    public void testEntityNotFound () throws Exception {
    	
    	ClientSurveySubmission css = new ClientSurveySubmission();
    	css.setId(clientId);
    	
				
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		String jsonStr ="";
		Map<String, String> json = new HashMap<String, String>();
		json.put("global_enrollment_id", globalEnrollmentId.toString());

		try {
			jsonStr = rootMapper.writeValueAsString(json);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		logger.info("JSon String: {}", jsonStr);  
        MockHttpServletRequestBuilder builder =
        		MockMvcRequestBuilders.put("/clientsurveysubmission/id/{id}" ,css.getId())
        			.headers(headers)
        			.contentType(MediaType.APPLICATION_JSON)
        			.content(jsonStr);
        MockHttpServletResponse response = this.mockMvc.perform(builder)
                    .andReturn().getResponse();
        logger.info("Status: " + response.getStatus());      
        assertEquals(500,response.getStatus());
        
    }
	
	}   

