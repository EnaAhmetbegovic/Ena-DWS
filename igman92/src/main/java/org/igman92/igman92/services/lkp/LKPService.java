package org.igman92.igman92.services.lkp;

import org.igman92.igman92.dao.BaseGenericDao;
import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.entity.base.LookUpEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LKPService <T extends LookUpEntity>{

    protected BaseGenericDao<T> lookupDao;

    @Autowired
    protected void setDao(BaseGenericDao<T> daoToSet) {
        lookupDao = daoToSet;
    }

    /**
     * @param clazz
     */
    public void setClazz(Class<T> clazz) {
        lookupDao.setClazz(clazz);
    }

    @Transactional(readOnly = true)
    public List<T> getAllEntities()  {
        try {
            return lookupDao.findAll();
        }catch (NoEntityFoundException e){
            throw new ProjectException(e);
        }
    }

    @Transactional(readOnly = true)
    public List<T> getByName(String name)   {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name parameter must be specified");
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "%" + name.toLowerCase() + "%");

        try {
            return lookupDao.query("from " + lookupDao.getClazz().getSimpleName() + " as e where lower(e.name) like :name", params);
        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        }
    }

    /**
     * @param newEntity
     * @return
     * @
     */
    @Transactional
    public T save(T newEntity)  {
        return lookupDao.save(newEntity);
    }

    /**
     * @param lookupEntityId
     * @return
     * @
     */
    @Transactional(readOnly = true)
    public T get(Short lookupEntityId)  {
        if (lookupEntityId == null) {
            throw new IllegalArgumentException("lookupEntityId parameter must be specified");
        }
        try {
            return lookupDao.getById(lookupEntityId);
        } catch (NoEntityFoundException e) {
            return null;
        }
    }

    /**
     * @param updatedLookupEntity
     * @return
     * @
     */
    @Transactional
    public T updateEntity(T updatedLookupEntity)  {
        if (updatedLookupEntity == null) {
            throw new IllegalArgumentException("updatedLookupEntity parameter must be specified");
        }

        T existingEntry;
        try {
            existingEntry = lookupDao.getById(updatedLookupEntity.getId());
        } catch (NoEntityFoundException e) {
            existingEntry = null;
        }
        if (existingEntry != null) {
            BeanUtils.copyProperties(updatedLookupEntity, existingEntry, "id", "optLock", "lastUpdated");

            return lookupDao.save(existingEntry);
        }

        return lookupDao.save(updatedLookupEntity);
    }

    /**
     * @param id
     * @
     */
    @Transactional
    public void delete(Short id)  {
        lookupDao.delete(id);
    }
}
