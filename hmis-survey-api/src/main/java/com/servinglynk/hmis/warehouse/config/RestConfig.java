package com.servinglynk.hmis.warehouse.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.servinglynk.hmis.warehouse.client.search.SearchServiceClient;
import com.servinglynk.hmis.warehouse.core.model.JSONObjectMapper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;
import com.servinglynk.hmis.warehouse.rest.SurveysController;
import com.servinglynk.hmis.warehouse.rest.interceptor.ApiAuthCheckInterceptor;
import com.servinglynk.hmis.warehouse.service.ClientValidator;
import com.servinglynk.hmis.warehouse.service.impl.ClientValidatorImpl;

@Configuration
@Import({com.servinglynk.hmis.warehouse.config.DatabaseConfig.class,
		 com.servinglynk.hmis.warehouse.client.config.SpringConfig.class})
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@ComponentScan("com.servinglynk.hmis.warehouse")
public class RestConfig extends WebMvcConfigurerAdapter {

	public void configureMessageConverters(
			List<HttpMessageConverter<?>> messageConverters) {

		MappingJackson2HttpMessageConverter jmc = new MappingJackson2HttpMessageConverter();
		jmc.setObjectMapper(new JSONObjectMapper());
		messageConverters.add(jmc);
		messageConverters.add(createXmlHttpMessageConverter());
		super.configureMessageConverters(messageConverters);
	}

	private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		return xmlConverter;
	}

	private ObjectMapper rootMapper()
	{
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	    mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
	    return mapper;
	}

	
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter jmc = new MappingJackson2HttpMessageConverter();
		jmc.setObjectMapper(new JSONObjectMapper());
		messageConverters.add(jmc);
		messageConverters.add(createXmlHttpMessageConverter());

		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}
	
	@Bean
	public SurveysController surveysController(){
		return new SurveysController();
	}
	
	@Bean
	public ApiAuthCheckInterceptor authCheckInterceptor(){
		return new ApiAuthCheckInterceptor();
	}
	
	@Bean
	public SessionHelper sessionHelper(){
		return new SessionHelper();
	}
	
	@Bean
	public TrustedAppHelper trustedAppHelper(){
		return new TrustedAppHelper();
	}
	
	@Bean
	public ClientValidatorImpl clientValidator(){
		return new ClientValidatorImpl();
	}
	
	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(authCheckInterceptor());
	    }
	
}