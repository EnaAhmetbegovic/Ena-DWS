package org.igman92.igman92.services.person;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.dto.person.PersonDTO;
import org.igman92.igman92.entity.impl.lkp.person.LKPGender;
import org.igman92.igman92.entity.impl.person.Parent;
import org.igman92.igman92.entity.impl.person.Person;
import org.igman92.igman92.entity.impl.person.PersonContact;
import org.igman92.igman92.services.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class PersonService {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PersonContactService personContactService;

    private IBaseDao<Person> personDao;
    private IBaseDao<LKPGender> genderDao;
    private IBaseDao<Parent> parentDao;

    @Autowired
    public void setPersonDao(IBaseDao<Person> daoToSet){
        personDao = daoToSet;
        personDao.setClazz(Person.class);
    }

    @Autowired
    private void setGenderDao(IBaseDao<LKPGender> dao) {
        this.genderDao = dao;
        this.genderDao.setClazz(LKPGender.class);
    }

    @Autowired
    private void setParentDao(IBaseDao<Parent> parentDao) {
        this.parentDao = parentDao;
        this.parentDao.setClazz(Parent.class);
    }

    @Transactional(readOnly = true)
    public boolean checkIfPersonExists(String jmbg) {
        if (jmbg == null) {
            throw new IllegalArgumentException("jmbg must be specified!");
        }

        try {
            HashMap<String, Object> params = new HashMap<>();
            params.put("jmbg", jmbg);

            personDao.query("SELECT p FROM Person p WHERE p.jmbg = :jmbg", params);

            return true;

        } catch (NoEntityFoundException e) {
            return false;
        }
    }

    @Transactional
    public Parent addParent(Parent parent)  {

        if (parent != null) {
            if (parent.getId() == null && parent.getFirstName() != null && parent.getLastName() != null) {

                parentDao.save(parent);
            }
        }

        return parent;
    }

    @Transactional
    public Person createPerson(PersonDTO newPersonDTO)  {
        Person newPerson = newPersonDTO.getPerson();

        if (newPerson == null || newPerson.getFirstName() == null || newPerson.getLastName() == null || newPerson.getJmbg() == null){
            throw new IllegalArgumentException("Person has to have first name, last name and JMBG");
        }

        if (checkIfPersonExists(newPerson.getJmbg())) {
            throw new ProjectException("Person with this JMBG already exists!");
        }

        try {
            LKPGender gender = genderDao.getById(newPerson.getGender().getId());
            newPerson.setGender(gender);

        } catch (NoEntityFoundException e) {
            throw new ProjectException("No valid gender found with given criteria!");
        }

        newPerson.setMother(addParent(newPerson.getMother()));
        newPerson.setFather(addParent(newPerson.getFather()));

        if (newPerson.getPlaceOfBirth() != null) {
            newPerson.setPlaceOfBirth(placeService.savePlace(newPerson.getPlaceOfBirth()));
        }
        if (newPerson.getResidence() != null) {
            newPerson.setResidence(placeService.saveResidence(newPerson.getResidence()));
        }

        personDao.save(newPerson);

        return newPerson;
    }

    public void setContacts(Person newPerson, PersonDTO newPersonDTO)  {
        if(newPersonDTO.getContacts() != null) {
            for (PersonContact contact : newPersonDTO.getContacts()) {
                personContactService.saveContact(newPerson, contact);
            }
        }
    }
}
