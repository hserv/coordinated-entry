package com.hserv.coordinatedentry.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hserv.coordinatedentry.entity.ResponseStorage;
import com.hserv.coordinatedentry.repository.ResponseRepository;

@Service
@Transactional
public class ResponseStorageService {

	private @Autowired ResponseRepository responseRepository;

	public ResponseStorage save(ResponseStorage responseStorage) {
		ResponseStorage responseStorageEntity = responseRepository
				.save(responseStorage);
		return responseStorageEntity;
	}

	public List<ResponseStorage> findAll() {
		return responseRepository.findAll();
	}

	public List<ResponseStorage> findByClientId(String clientId) {
		return responseRepository.findByClientId(clientId);
	}

	/*public List<ResponseStorage> findByClientIdAndSurveyId(String clientId, Integer surveyId) {
		return responseRepository.findByClientIdAndSurveyId(clientId, surveyId);
	}*/
	
	public List<ResponseStorage> findByClientIdAndSurveyId(String clientId, Integer responseId, Integer surveyId) {
		return responseRepository.findByClientIdAndResponseIdAndSurveyId(clientId, responseId, surveyId);
	}
}
