package com.hserv.coordinatedentry.housingmatching.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.dao.MatchProcessLogRepository;
import com.hserv.coordinatedentry.housingmatching.dao.MatchReservationsRepository;
import com.hserv.coordinatedentry.housingmatching.entity.Match;
import com.hserv.coordinatedentry.housingmatching.entity.MatchProcessLogEntity;
import com.hserv.coordinatedentry.housingmatching.service.MatchProcessLogService;

@Component("matchProcessLogService")
public class MatchProcessLogServiceImpl implements MatchProcessLogService {

	private UUID processId;

	@Autowired
	MatchProcessLogRepository logRepository;

	@Autowired
	MatchReservationsRepository matchReservationsRepository;

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

	private UUID clientId;
	private UUID housingUnitId;

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public UUID getHousingUnitId() {
		return housingUnitId;
	}

	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
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
	public Page<MatchProcessLogEntity> getMatchProcessLog(UUID processId, UUID housingUnitId, UUID projectId,
			UUID clientId, UUID matchId, Pageable pageable) {

		List<MatchProcessLogEntity> logData = new ArrayList<MatchProcessLogEntity>();
		
		Session session = entityManager.unwrap(Session.class);
		
		Match match =null; 
		if(matchId!=null) match = matchReservationsRepository.findOne(matchId);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(MatchProcessLogEntity.class);
		if(match!=null && match.getProcessId()!=null)
			criteria.add(Restrictions.eq("processId", match.getProcessId()));
		if(match!=null && match.getHousingUnitId()!=null)
			criteria.add(Restrictions.eq("housingUnitId", match.getHousingUnitId()));
		if(match!=null && match.getEligibleClient()!=null)
			criteria.add(Restrictions.eq("clientId", match.getEligibleClient().getClientId()));

		if(housingUnitId!=null)
			criteria.add(Restrictions.eq("housingUnitId", housingUnitId));
		if(clientId!=null)
			criteria.add(Restrictions.eq("clientId", clientId));
		if(processId!=null)
			criteria.add(Restrictions.eq("processId", processId));
		if(projectId!=null)
			criteria.add(Restrictions.eq("projectId", projectId));
		
		criteria.addOrder(Order.asc("dateCreated"));
		
		Criteria eCriteria = criteria.getExecutableCriteria(session);
		Long total = 	(long) eCriteria.list().size();
		if(total!=0){
			eCriteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
			eCriteria.setMaxResults(pageable.getPageSize());		
			logData = eCriteria.list();
		}
			
		
		return new PageImpl<MatchProcessLogEntity>(logData, pageable,total);
		
	/*	Sort sort = new Sort(Direction.ASC, "dateCreated");
		PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
		return logRepository.findAll(specification, pageRequest);*/
	}
}