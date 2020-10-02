package org.igman92.igman92.dao;

import org.springframework.util.CollectionUtils;
import org.igman92.igman92.dao.exceptions.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseAbstractDao<T extends IBaseEntity> implements IBaseDao<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> clazz;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    private void validateState() {
        if (clazz == null) {
            throw new IllegalStateException("DAO class not properly initialized, missing clazz");
        }
    }

    @Override
    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    public List<T> findAll() {
        validateState();

        List<T> resultList = entityManager.createQuery("SELECT e FROM " + clazz.getName() + " e", clazz).getResultList();

        if (CollectionUtils.isEmpty(resultList)) {
            throw new NoEntityFoundException("No entities found of type " + clazz.getSimpleName());
        }

        return resultList;
    }

    @Override
    public T save(T entity)  {
        try {
            if (entity.getId() == null) {
                entityManager.persist(entity);

                return entity;
            } else {
                return entityManager.merge(entity);
            }
        } catch (Exception e) {
            throw new ProjectException(e);
        }
    }

    @Override
    public T getById(Serializable id) {
        validateState();

        T result = entityManager.find(clazz, id);
        if (result == null) {
            throw new NoEntityFoundException("No entity found of type " + clazz.getSimpleName());
        }

        return result;
    }

    @Override
    public void delete(T entity)  {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            throw new ProjectException(e);
        }
    }

    @Override
    public void delete(Serializable id)  {
        validateState();

        try {
            T entity = entityManager.find(clazz, id);
            entityManager.remove(entity);
        } catch (Exception e) {
            throw new ProjectException(e);
        }
    }

    @Override
    public List<T> safeQuery(String query, Map<String, Object> params) {
        validateState();

        TypedQuery<T> createQuery = entityManager.createQuery(query, this.clazz);
        params.forEach((k, v) -> createQuery.setParameter(k, v));

        return createQuery.getResultList();
    }

    @Override
    public List<T> safeQuery(String query, Map<String, Object> params, int maxResults) {
        validateState();

        if (maxResults <= 0) {
            throw new IllegalArgumentException("maxResults parameter must be greater than 0");
        }
        TypedQuery<T> createQuery = entityManager.createQuery(query, this.clazz).setMaxResults(maxResults);
        params.forEach((k, v) -> createQuery.setParameter(k, v));

        return createQuery.getResultList();
    }

    @Override
    public List<T> query(String query, Map<String, Object> params) {

        List<T> resultList = this.safeQuery(query, params);

        if (CollectionUtils.isEmpty(resultList)) {
            throw new NoEntityFoundException("No entity found of type " + clazz.getSimpleName());
        }
        return resultList;
    }

    public List<T> query(String query, Map<String, Object> params, int maxResults) {

        List<T> resultList = this.safeQuery(query, params, maxResults);

        if (CollectionUtils.isEmpty(resultList)) {
            throw new NoEntityFoundException("No entity found of type " + clazz.getSimpleName());
        }
        return resultList;
    }

    @Override
    public List<T> query(String query) {
        return query(query, Collections.emptyMap());
    }

    @Override
    public T querySingleResult(String query, Map<String, Object> params) {
        validateState();

        try {
            TypedQuery<T> createQuery = entityManager.createQuery(query, this.clazz);
            params.forEach((k, v) -> createQuery.setParameter(k, v));
            return createQuery.getSingleResult();
        }
        catch (NoResultException e) {
            throw new NoEntityFoundException("No entity found of type " + clazz.getSimpleName());
        }
        catch (NonUniqueResultException e) {
            throw new MultipleEntitiesFoundException("Found more than one entity of type " + clazz.getSimpleName());
        }
    }

    @Override
    public List
    safeQueryToDTO(String query, Map<String, Object> params) {
        validateState();

        Query createQuery = entityManager.createQuery(query);
        params.forEach((k, v) -> createQuery.setParameter(k, v));

        return createQuery.getResultList();
    }

    @Override
    public List queryAndTransformToDTO(String query, Map<String, Object> params) {

        List resultList = this.safeQueryToDTO(query, params);

        if (CollectionUtils.isEmpty(resultList)) {
            throw new NoEntityFoundException("No entity found of type " + clazz.getSimpleName());
        }

        return resultList;
    }

    @Override
    public Long count() {
        return this.count("SELECT COUNT(id) FROM " + this.clazz.getSimpleName(), new HashMap<>());
    }

    @Override
    public Long count(String query, Map<String, Object> params) {
        validateState();

        TypedQuery<Long> createQuery = entityManager.createQuery(query, Long.class);
        params.forEach((k, v) -> createQuery.setParameter(k, v));
        return createQuery.getSingleResult();
    }

}
