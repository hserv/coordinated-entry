package com.hserv.coordinatedentry.housingmatching.dao;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.core.model.Account;

public class BaseRepositoryImpl<T, ID extends Serializable>
			extends SimpleJpaRepository<T, ID>   implements BaseRepository<T, ID> {

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
        this.entityManager = entityManager;
	}

	private final EntityManager entityManager;
	
	 public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
	        super(domainClass, entityManager);
	        this.entityManager = entityManager;
	    }



	@Override
	@Transactional
	public void delete(T entity)  {
			try {
				BeanUtils.setProperty(entity, "deleted", true);
				BeanUtils.setProperty(entity, "dateUpdated", LocalDateTime.now());
				Account account = SecurityContextUtil.getUserAccount();
				if(account!=null)
						BeanUtils.setProperty(entity, "userId", account.getAccountId());

				entityManager.merge(entity);
			} catch (Exception e) {
				entityManager.detach(entity);
				entityManager.flush();
			} 
	}
	@Transactional
	public void delete(Iterable<? extends T> entities){
		for (T entity : entities) {
			delete(entity);
		}
	}
}