package com.ochaumont.demo.skillknowledge.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * DAO generique permettant les operations CRUD de base
 */
public class CrudDaoImpl<T> implements CrudDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityClass(Class<T> clazz) {
		this.entityClass = clazz;
	}

	public final T save(T t) {
		if(entityManager.contains(t)){
			T newT = entityManager.merge(t);
			return newT;
		}
		else{
			entityManager.persist(t);
			return t;
		}
	}

	public T get(Long id) {
		return (T) entityManager.find(entityClass, id);
	}

	public List<T> getBy(String property, Object value) {

		Query query = entityManager.createQuery("from "+entityClass.getName()+" where "+property+"=:property");
		query.setParameter("property", value);

		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) query.getResultList();
		return result;
	}

	public List<T> getAll() {
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) entityManager.createQuery("from "+entityClass.getName()).getResultList();
		return result;
	}

	public List<T> getAll(int start, int max) {
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) entityManager.createQuery("from "+entityClass.getName()).setFirstResult(start).setMaxResults(max).getResultList();
		return result;
	}

	public final void delete(T t) {
		entityManager.remove(t);    
	}


	public void clear(){
		entityManager.clear();
	}

	public void flush(){
		entityManager.flush();
	}

	public boolean contains(Object t) {
		return entityManager.contains(t);
	}

}

