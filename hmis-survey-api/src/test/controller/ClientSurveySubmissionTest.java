package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.servinglynk.hmis.warehouse.config.RestConfig;
import com.servinglynk.hmis.warehouse.model.ClientSurveySubmissionEntity;
import com.servinglynk.hmis.warehouse.rest.ClientSurveySubmissionController;
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
	
	@Test
	public void testUpdateRecord() throws Exception {
		// create new record
		ClientSurveySubmissionEntity csse = new ClientSurveySubmissionEntity();
		csse.setClientId(UUID.randomUUID());
		csse.setSubmissionId(UUID.randomUUID());
		csse.setSurveyId(UUID.randomUUID());
		UUID geid = UUID.randomUUID();
		ClientSurveySubmissionEntity result = serviceFactory.getClientSurveySubmissionService().createClientSurveySubmissionEntity(csse);
		//update record		
		result.setGlobalEnrollmentId(geid);
		serviceFactory.getClientSurveySubmissionService().updateClientSurveySubmissionEntity(result);
		//retrieve updated
		result = serviceFactory.getClientSurveySubmissionService().getClientSurveySubmissionEntitybyId(result.getId());
		
		//compare updated with provided GlobalEnrollmentId
		assertEquals(result.getGlobalEnrollmentId(),geid);
	}
}
