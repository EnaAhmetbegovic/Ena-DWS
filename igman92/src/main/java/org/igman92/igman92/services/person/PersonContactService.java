package org.igman92.igman92.services.person;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.entity.impl.lkp.LKPContactType;
import org.igman92.igman92.entity.impl.person.Person;
import org.igman92.igman92.entity.impl.person.PersonContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class PersonContactService {

    @Autowired
    private IBaseDao<PersonContact> personContactDao;

    @Autowired
    private IBaseDao<LKPContactType> contactTypeDao;

    @Autowired
    public void setPersonContactDao(IBaseDao<PersonContact> daoToSet) {
        personContactDao = daoToSet;
        personContactDao.setClazz(PersonContact.class);
    }

    @Autowired
    public void setContactTypeDao(IBaseDao<LKPContactType> daoToSet) {
        contactTypeDao = daoToSet;
        contactTypeDao.setClazz(LKPContactType.class);
    }

    @Transactional(readOnly = true)
    protected boolean contactAlreadyInserted(Long personId, Short contactTypeId, String value) {

        if (personId == null || contactTypeId == null || value == null) {

            throw new IllegalArgumentException("Method parameters must not be of null value!");
        }

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("personId", personId);
            params.put("contactTypeId", contactTypeId);
            params.put("value", value);

            personContactDao.query("SELECT pc FROM PersonContact pc " +
                    "WHERE pc.person.id = :personId AND pc.contactType.id = :contactTypeId " +
                    "AND pc.value = :value", params);

            return true;

        } catch (NoEntityFoundException e) {
            return false;
        }
    }

    @Transactional
    public PersonContact saveContact(Person person, PersonContact newContact)  {
        if (person == null || newContact == null || newContact.getValue() == null || newContact.getContactType() == null) {
            throw new IllegalArgumentException("person parameter must be specified, newContact parameter must contain type and value!");
        }
        try {

            newContact.setPerson(person);
            newContact.setContactType(contactTypeDao.getById(newContact.getContactType().getId()));

            return personContactDao.save(newContact);
        } catch (NoEntityFoundException e){
            throw new ProjectException(e);
        }
    }

    @Transactional
    public void deleteContact(Long personId, Long contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("contactId parameter must be specified!");
        }

        try {
            PersonContact personContact = personContactDao.getById(contactId);

            if(personContact.getPerson().getId().equals(personId)) {
                personContactDao.delete(contactId);

            } else throw new ProjectException("Access not allowed, cannot delete this contact!");

        } catch (NoEntityFoundException e) {
            throw new ProjectException(e);
        }
    }

    @Transactional
    public List<PersonContact> getPersonContacts(Long personId) {
        if (personId == null) {
            throw new IllegalArgumentException("personId parameter must be specified!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("personId", personId);

            return personContactDao.query("SELECT pc FROM PersonContact pc " +
                    "WHERE pc.person.id = :personId", params);

        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        }
    }
}
