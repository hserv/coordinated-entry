package com.servinglynk.hmis.warehouse.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.expression.function.LocateFunction;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.util.SecurityContextUtil;


@Component
public class QueryExecutorImpl  implements QueryExecutor{
	@Autowired
	SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
		
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void load(Object entity,Serializable id){
		getCurrentSession().load(entity, id);
	}
	
	public Object get(Class<?> entity,Serializable id){
			return getCurrentSession().get(entity, id);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Object insert(Object entity) {
		try{
			BeanUtils.setProperty(entity,"projectGroupCode",SecurityContextUtil.getUserAccount().getProjectGroup().getProjectGroupCode());
			BeanUtils.setProperty(entity, "updatedAt", LocalDateTime.now());
			BeanUtils.setProperty(entity, "createdAt", LocalDateTime.now());
			BeanUtils.copyProperty(entity, "user", SecurityContextUtil.getUserAccount().getAccountId());
		return getCurrentSession().save(entity);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public Object update(Object entity) {
		try {
			BeanUtils.setProperty(entity, "updatedAt", LocalDateTime.now());
			BeanUtils.copyProperty(entity, "user", SecurityContextUtil.getUserAccount().getAccountId());
			getCurrentSession().update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public Object merge(Object entity) {
		getCurrentSession().merge(entity);
		return null;
	}

	public void delete(Object entity) {		
			try {
				BeanUtils.copyProperty(entity, "user", SecurityContextUtil.getUserAccount().getAccountId());
				BeanUtils.copyProperty(entity, "updatedAt", LocalDateTime.now());
				BeanUtils.copyProperty(entity, "deleted",true);
				getCurrentSession().update(entity);
			} catch (Exception e) {
				getCurrentSession().delete(entity);
			}
	}

	public Object insertOrUpdate(Object entity) {
		getCurrentSession().saveOrUpdate(entity);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.IQueryExecutor#executeNQ(java.lang.String, java.lang.String[], java.lang.Object[])
	 * 
	 * This method is used to execute Named queries . All DAO can call this method for executing Named Queries. If the number of parameters do not match with 
	 * number of values, throws "Illegal Argument Exception". 
	 */
	public int executeNQ(String queryName, String[] paramNames, Object[] paramValues) {
		if (paramNames != null && paramValues != null 	&& paramNames.length != paramValues.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of paramValues array");
		}

		Session session = getCurrentSession();
		Query queryObject = session.getNamedQuery(queryName);
		if (paramValues != null) {
			for (int param = 0; param< paramValues.length; param++) {
				applyNamedParameterToQuery(queryObject, paramNames[param], 	paramValues[param]);
			}
		}
		return queryObject.executeUpdate();
		
	}
	
/* 
 * (non-Javadoc)
 * @see com.servinglynk.hmis.warehouse.dao.IQueryExecutor#executeUpdateHQL(java.lang.String, java.lang.String[], java.lang.Object[])
 * 
 * This method is used to execute Hibernate queries . All DAO can call this method for executing Hibernate Queries. If the number of parameters do not match with 
 * number of values, throws "Illegal Argument Exception". 
 */
public int executeHQL(String queryName, String[] paramNames, Object[] paramValues) {
		
	if (paramNames != null && paramValues != null
			&& paramNames.length != paramValues.length) {
		throw new IllegalArgumentException("Length of paramNames array must match length of paramValues array");
	}

	Session session = getCurrentSession();
	Query queryObject = session.createQuery(queryName);
	if (paramValues != null) {
		for (int param = 0;  param< paramValues.length; param++) {
			applyNamedParameterToQuery(queryObject, paramNames[param],	paramValues[param]);
		}
	}
	return queryObject.executeUpdate();
		
	}

protected List<?> findByNamedQueryAndNamedParam(String queryName,
		String[] paramNames, Object[] paramValues, int maxResults, int firstResult) {

	if (paramNames != null && paramValues != null
			&& paramNames.length != paramValues.length) {
		throw new IllegalArgumentException(
				"Length of paramNames array must match length of paramValues array");
	}

	Session session = getCurrentSession();
	Query queryObject = session.getNamedQuery(queryName);
	if (paramValues != null) {
		for (int i = 0; i < paramValues.length; i++) {
			applyNamedParameterToQuery(queryObject, paramNames[i],
					paramValues[i]);
		}
	}

	if (maxResults != -1) {
		queryObject.setMaxResults(maxResults);
	}
	if (firstResult != -1) {
		queryObject.setFirstResult(firstResult);
	}

	return queryObject.list();
}

	public List<?> findByNamedQuery(String queryName) {
		return null;
		//TBD
	}
	
	public List<?> list(String entityName){
		Session session = getCurrentSession();
		return session.createQuery("from "+entityName).list();
	}
	
	
	public List<?> findByNativeSQL(String query, Class<?> className) {
		Session session = getCurrentSession();
		
		Query queryObject = session.createSQLQuery(query).addEntity(className);
		return queryObject.list();
	}
	

	public List<?> findByCriteria(DetachedCriteria detachedCriteria){
				detachedCriteria.add(Restrictions.eq("deleted", false));
				detachedCriteria.add(Restrictions.eq("projectGroupCode",  SecurityContextUtil.getUserAccount().getProjectGroup().getProjectGroupCode()));
				return detachedCriteria.getExecutableCriteria(getCurrentSession()).list();
	}
	
	public List<?> findByCriteriaWithOutDelete(DetachedCriteria detachedCriteria){
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		return criteria.list();
	}
	

	public List<?> findByCriteria(DetachedCriteria detachedCriteria,Integer firstResult,Integer maxResults){
		detachedCriteria.add(Restrictions.eq("deleted", false));
		detachedCriteria.add(Restrictions.eq("projectGroupCode",  SecurityContextUtil.getUserAccount().getProjectGroup().getProjectGroupCode()));
		Criteria criteria = detachedCriteria.getExecutableCriteria(getCurrentSession());
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);		
		return criteria.list();
		
	}
			
	public long countRows(DetachedCriteria dCriteria, ProjectionList projectionList){
		 return (long)0;
		 //TBD
	}
	

	public long countRows(DetachedCriteria dCriteria){
		dCriteria.add(Restrictions.eq("deleted", false));
		dCriteria.add(Restrictions.eq("projectGroupCode",  SecurityContextUtil.getUserAccount().getProjectGroup().getProjectGroupCode()));
		dCriteria.setProjection(Projections.rowCount());

		return (Long) dCriteria.getExecutableCriteria(getCurrentSession()).uniqueResult();
		 //TBD
	}
	
	protected Object[] executeNativeSQL(Class<?> className){
		return null;
	}
	
	public List<?> findByNQandNP(String query,String[] params, Object[] values){
		return null;
		//TBD
	}

	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public List<?> findByExample(Object entity) {
		Example example = Example.create( entity );
		example.enableLike(MatchMode.ANYWHERE);
		Criteria criteria = getCurrentSession().createCriteria( entity.getClass() );
		criteria.add( example );
		return criteria.add(example).list();
	}
	
	// 03/18/2015 - Applying parameters query - parameteres can be sent normally as a collection, array of objects or as a value.
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value) {
		if (value instanceof Collection) {
				queryObject.setParameterList(paramName, (Collection<?>) value);
		} else if (value instanceof Object[]) {
				queryObject.setParameterList(paramName, (Object[]) value);
		} else {
				queryObject.setParameter(paramName, value);
		}
	}


	protected Object findUniqueResultByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] paramValues) {

		if (paramNames != null && paramValues != null
				&& paramNames.length != paramValues.length) {
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of paramValues array");
		}

		Session session = getCurrentSession();
		Query queryObject = session.getNamedQuery(queryName);
			for (int i = 0; i < paramValues.length; i++) {
				if (paramValues != null) {
				applyNamedParameterToQuery(queryObject, paramNames[i],
						paramValues[i]);
			}
		}
		return  queryObject.uniqueResult();
	}
	
	protected Object findUniqueObjectByNamedQueryAndNamedParam(String queryName, String[] paramNames, Object[] paramValues) {

		if (paramNames != null && paramValues != null && paramNames.length != paramValues.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of paramValues array");
		}

		Session session = getCurrentSession();
		Query queryObject = session.getNamedQuery(queryName);
		if (paramValues != null) {
			for (int i = 0; i < paramValues.length; i++) {
				applyNamedParameterToQuery(queryObject, paramNames[i], paramValues[i]);
			}
		}
		return queryObject.uniqueResult();
	}
	

}
