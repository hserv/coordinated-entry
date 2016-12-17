package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.dao.MatchProcessLogRepository;
import com.hserv.coordinatedentry.housingmatching.entity.MatchProcessLogEntity;
import com.hserv.coordinatedentry.housingmatching.service.MatchProcessLogService;

@Component("matchProcessLogService")
public class MatchProcessLogServiceImpl implements MatchProcessLogService {

	
	private UUID processId;

	@Autowired
	MatchProcessLogRepository logRepository;

	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	Environment env;

	public UUID getProcessId() {
		return processId;
	}

	public void setProcessId(UUID processId) {
		this.processId = processId;
	}

	public void log(String messageId, Object[] args, boolean status, UUID housingUnitId, UUID projectId,
			UUID clientId) {
		FormattingTuple tp = null;

		try {
			tp = MessageFormatter.arrayFormat(env.getProperty(messageId), args);

		} catch (Exception e) {
			tp = MessageFormatter.arrayFormat("Message foormat tewst {} with tuple {}", new Object[] { "param1" });
		}

		MatchProcessLogEntity entity = new MatchProcessLogEntity();
		entity.setClientId(clientId);
		entity.setHousingUnitId(housingUnitId);
		entity.setProjectId(projectId);
		entity.setStatus(status);
		entity.setStatusMessage(tp.getMessage());
		entity.setProcessId(this.processId);
		logRepository.save(entity);
	}

	@Override
	public Page<MatchProcessLogEntity> getMatchProcessLog(UUID processId,UUID housingUnitId,UUID projectId,UUID clientId,Pageable pageable) {
	
		Specification<MatchProcessLogEntity> specification = null;

		
		Specification<MatchProcessLogEntity> processIdSpec = Specifications.where(new Specification<MatchProcessLogEntity>() {
			@Override
			public Predicate toPredicate(Root<MatchProcessLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("processId"), processId));
			}
		});
		
		
		Specification<MatchProcessLogEntity> projectIdSpec = Specifications.where(new Specification<MatchProcessLogEntity>() {
			@Override
			public Predicate toPredicate(Root<MatchProcessLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("projectId"), projectId));
			}
		});
		
		
		Specification<MatchProcessLogEntity> housingUnitIdSpec = Specifications.where(new Specification<MatchProcessLogEntity>() {
			@Override
			public Predicate toPredicate(Root<MatchProcessLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("housingUnitId"), housingUnitId));
			}
		});
		
		
		Specification<MatchProcessLogEntity> clientIdSpec = Specifications.where(new Specification<MatchProcessLogEntity>() {
			@Override
			public Predicate toPredicate(Root<MatchProcessLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("clientId"), clientId));
			}
		});
		
		if(processId!=null) specification = Specifications.where(specification).and(processIdSpec);
		if(projectId!=null) specification = Specifications.where(specification).and(projectIdSpec);
		if(clientId!=null) specification = Specifications.where(specification).and(clientIdSpec);
		if(housingUnitId!=null) specification = Specifications.where(specification).and(housingUnitIdSpec);
		Sort sort = new Sort(Direction.ASC,"dateCreated");
		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(),sort);
		return logRepository.findAll(specification,pageRequest);
	}
}