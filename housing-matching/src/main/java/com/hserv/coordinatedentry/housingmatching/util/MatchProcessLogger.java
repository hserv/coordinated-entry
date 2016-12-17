package com.hserv.coordinatedentry.housingmatching.util;

import java.util.UUID;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.dao.MatchProcessLogRepository;
import com.hserv.coordinatedentry.housingmatching.entity.MatchProcessLogEntity;

@Component
public class MatchProcessLogger {

	private UUID processId;

	@Autowired
	MatchProcessLogRepository logRepository;

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
			System.out.println("  Message "+messageId+" test message "+tp.getMessage()+" test");
			if(env.getProperty(messageId).isEmpty()) System.out.println("Empty property");
		} catch (Exception e) {
			System.out.println("Exception message property "+messageId);
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
}