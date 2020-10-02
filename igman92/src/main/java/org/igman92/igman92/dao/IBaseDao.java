package org.igman92.igman92.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T extends IBaseEntity> {

    void setClazz(Class<T> clazz);

    Class<T> getClazz();

    List<T> findAll();

    T save(T entity) ;

    T getById(Serializable id);

    void delete(T entity) ;

    void delete(Serializable id) ;

    List<T> query(String query);

    List<T> query(String query, Map<String, Object> params);

    List<T> safeQuery(String query, Map<String, Object> params, int maxResults);

    List<T> safeQuery(String query, Map<String, Object> params);

    T querySingleResult(String query, Map<String, Object> params);

    List safeQueryToDTO(String query, Map<String, Object> params);

    List queryAndTransformToDTO(String query, Map<String, Object> params);

    Long count() ;

    Long count(String query, Map<String, Object> params) ;
}
