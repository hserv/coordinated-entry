package com.hserv.coordinatedentry.housingmatching.translator;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.hserv.coordinatedentry.housingmatching.entity.ClientInfo;
import com.hserv.coordinatedentry.housingmatching.model.ClientInfoModel;

@Component
public class ClientInfoTranslator {

	public ClientInfoModel translate(ClientInfo clientInfo) {
		ClientInfoModel clientInfoModel = null;
		if (null != clientInfo) {
			clientInfoModel = new ClientInfoModel();
			clientInfoModel.setClientId(clientInfo.getClientId().toString());
			clientInfoModel.setFirstName(clientInfo.getFirstName());
			clientInfoModel.setMiddleName(clientInfo.getMiddleName());
			clientInfoModel.setLastName(clientInfo.getLastName());
			clientInfoModel.setContactEmail(clientInfo.getContactEmail());
			clientInfoModel.setDateCreated(clientInfo.getDateCreated());
			clientInfoModel.setDateUpdated(clientInfo.getDateUpdated());
			clientInfoModel.setDob(clientInfo.getDob());
			clientInfoModel.setEthnicity(clientInfo.getEthnicity());
			clientInfoModel.setNameSuffix(clientInfo.getNameSuffix());
			clientInfoModel.setGender(clientInfo.getGender());
			clientInfoModel.setOtherGender(clientInfo.getOtherGender());
			clientInfoModel.setRace(clientInfo.getRace());
			clientInfoModel.setSsn(clientInfo.getSsn());
			// clientInfoModel.setUserId(clientInfo.getUserId().toString());
			clientInfoModel.setVeteranStatus(clientInfo.getVeteranStatus());
		}

		return clientInfoModel;

	}

	public ClientInfo translate(ClientInfoModel clientInfoModel) {
		ClientInfo clientInfo = null;

		if (null != clientInfoModel) {
			clientInfo = new ClientInfo();
		} else {
			return null;
		}
		clientInfo.setClientId(UUID.fromString(clientInfoModel.getClientId()));
		clientInfo.setFirstName(clientInfoModel.getFirstName());
		clientInfo.setContactEmail(clientInfoModel.getContactEmail());
		return clientInfo;
	}

}
