package org.igman92.igman92.services.lkp;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.entity.impl.lkp.LKPContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LKPContactTypeService {

    @Autowired
    private IBaseDao<LKPContactType> contactTypeDao;

    @Autowired
    public void setContactTypeDao(IBaseDao<LKPContactType> daoToSet){
        contactTypeDao = daoToSet;
        contactTypeDao.setClazz(LKPContactType.class);
    }

    @Transactional
    public List<LKPContactType> getPersonalContactTypes() {

        try {

            String query = "SELECT ct FROM LKPContactType ct " +
                    "WHERE ct.personal = 1";

            return contactTypeDao.query(query);

        } catch (Exception e) {
            throw new ProjectException("Could not get personal contact types! " + e);
        }
    }
}
