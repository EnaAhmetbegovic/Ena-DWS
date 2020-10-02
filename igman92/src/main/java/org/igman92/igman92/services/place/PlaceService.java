package org.igman92.igman92.services.place;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.MultipleEntitiesFoundException;
import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.entity.impl.lkp.place.LKPCountry;
import org.igman92.igman92.entity.impl.lkp.place.LKPEntity;
import org.igman92.igman92.entity.impl.lkp.place.LKPMunicipality;
import org.igman92.igman92.entity.impl.place.Place;
import org.igman92.igman92.entity.impl.place.Residence;
import org.igman92.igman92.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlaceService {

    private IBaseDao<LKPEntity> entityDao;
    private IBaseDao<Place> placeDao;
    private IBaseDao<LKPCountry> countryDao;
    private IBaseDao<LKPMunicipality> municipalityDao;
    private IBaseDao<Residence> residenceDao;

    @Autowired
    public void setPlaceDao(IBaseDao<Place> daoToSet) {
        this.placeDao = daoToSet;
        this.placeDao.setClazz(Place.class);
    }
    @Autowired
    public void setEntityDao(IBaseDao<LKPEntity> daoToSet) {
        this.entityDao = daoToSet;
        this.entityDao.setClazz(LKPEntity.class);
    }

    @Autowired
    public void setCountryDao(IBaseDao<LKPCountry> daoToSet) {
        this.countryDao = daoToSet;
        this.countryDao.setClazz(LKPCountry.class);
    }

    @Autowired
    public void setMunicipalityDao(IBaseDao<LKPMunicipality> daoToSet) {
        this.municipalityDao = daoToSet;
        this.municipalityDao.setClazz(LKPMunicipality.class);
    }

    @Autowired
    public void setResidenceDao(IBaseDao<Residence> daoToSet) {
        this.residenceDao = daoToSet;
        this.residenceDao.setClazz(Residence.class);
    }

    private boolean isValidBosnianPlace(Place place) {
        if (place.getCountry() == null) {
            LKPCountry existingCountry = countryDao.getById(place.getCountry().getId());

            if (!existingCountry.get_key().equals("bih")) return false;
        }

        if (place.getEntity() == null) {
            return (place.getCanton() == null || place.getMunicipality() == null);
        }

        LKPEntity existingEntity = entityDao.getById(place.getEntity().getId());

        if (existingEntity.get_key().equals("fbih")) {

            if (place.getCanton() == null) {
                return (place.getMunicipality() == null);

            } else {

                LKPMunicipality municipality = municipalityDao.getById(place.getMunicipality().getId());

                return place.getCanton().getId().equals(municipality.getCanton().getId()) &&
                        place.getEntity().getId().equals(municipality.getEntity().getId()) &&
                        place.getCountry().getId().equals(municipality.getCountry().getId());
            }

        } else if (existingEntity.get_key().equals("rs")) {

            if (place.getCanton() == null) {
                LKPMunicipality municipality = municipalityDao.getById(place.getMunicipality().getId());

                return (place.getCanton() == null && municipality.getCanton() == null) &&
                        place.getEntity().getId().equals(municipality.getEntity().getId()) &&
                        place.getCountry().getId().equals(municipality.getCountry().getId());

            } return false;

        } else if (existingEntity.get_key().equals("bd")) {
            return (place.getCanton() == null && place.getMunicipality() == null);
        }

        return false;
    }

    private void isValidPlace(Place place)  {

        try {
            LKPCountry bosnia = countryDao.querySingleResult("SELECT c FROM LKPCountry c WHERE c._key = 'bih'", new HashMap<>());

            if (place.getCountry().getId().equals(bosnia.getId())) {
                if(!isValidBosnianPlace(place)) {
                    throw new IllegalArgumentException("Invalid place parameter!");
                }

            } else if (place.getCanton() != null || place.getEntity() != null || place.getMunicipality() != null) {
                throw new IllegalArgumentException("Invalid country inserted!");
            }

        } catch (Exception e) {
            throw new ProjectException(e);
        }
    }

    @Transactional(readOnly = true)
    public Place findByValues(Place newPlace) {

        if (newPlace == null || newPlace.getName() == null) {
            return null;
        }

        try {
            Map<String, Object> params = new HashMap<>();

            StringBuilder sb = new StringBuilder("select p from Place p where 1=1");

            if (newPlace.getName() != null) {
                params.put("name", newPlace.getName());
                sb.append(" and p.name = :name");
            }

            if (newPlace.getMunicipality() != null && newPlace.getMunicipality().getId() != null) {

                params.put("municipalityId", newPlace.getMunicipality().getId());
                sb.append(" and p.municipality.id = :municipalityId");
            }

            if (newPlace.getCanton() != null && newPlace.getCanton().getId() != null) {

                params.put("cantonId", newPlace.getCanton().getId());
                sb.append(" and p.canton.id = :cantonId");
            }

            if (newPlace.getEntity() != null && newPlace.getEntity().getId() != null) {

                params.put("entityId", newPlace.getEntity().getId());
                sb.append(" and p.entity.id = :entityId");
            }

            if (newPlace.getCountry() != null && newPlace.getCountry().getId() != null) {

                params.put("countryId", newPlace.getCountry().getId());
                sb.append(" and p.country.id = :countryId");
            }

            return placeDao.querySingleResult(sb.toString(), params);

        } catch (NoEntityFoundException | MultipleEntitiesFoundException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Residence findByValues(Residence residence) {

        if (residence == null || residence.getStreet() == null) {
            return null;
        }

        Place place = this.findByValues(residence.getPlace());

        if (place == null) {
            return null;
        }

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("street", residence.getStreet());
            params.put("placeId", place.getId());

            return residenceDao.querySingleResult(
                    "SELECT r FROM Residence r " +
                            "WHERE r.street = :street " +
                            "AND r.place.id = :placeId"
                    , params);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Place savePlace(Place newPlace)  {

        if (newPlace == null || newPlace.getName() == null || newPlace.getCountry() == null) {
            throw new IllegalArgumentException("Place parameter must have name.");
        }
        Place existingPlace = findByValues(newPlace);

        if(existingPlace != null) {
            return existingPlace;
        }
        isValidPlace(newPlace);

        Place place = new Place();
        CopyUtil.copyOnly(newPlace, place, "name", "municipality", "canton", "entity", "country");
        return placeDao.save(place);
    }

    @Transactional
    public Residence saveResidence(Residence residence)  {

        if (residence == null || residence.getStreet() == null || residence.getPlace() == null) {
            throw new IllegalArgumentException("Residence parameter must have street and an existing place.");
        }

        Residence existingResidence = this.findByValues(residence);

        if (existingResidence != null) {
            return existingResidence;
        }

        Residence residenceToSave = new Residence();
        residenceToSave.setStreet(residence.getStreet());
        residenceToSave.setPlace(this.savePlace(residence.getPlace()));
        return residenceDao.save(residenceToSave);
    }
}
