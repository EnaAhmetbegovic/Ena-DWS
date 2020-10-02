import axios from 'axios';
import moment from 'moment';

const dropdownMapper = res =>
  res.data.map(el => ({
    value: el.id,
    key: el.id,
    text: el.name
  }));

const fetchPersonalContacts = () =>
  axios
    .get('http://localhost:8081/contact-types/personal/')
    .then(dropdownMapper);

const fetchGendersWithDefaultKeys = () =>
  axios.get('http://localhost:8081/genders').then(res => {
    const defaultMaleGenderId = res.data.find(el => el.abbreviation === 'M').id;
    const defaultFemaleGenderId = res.data.find(el => el.abbreviation === 'F')
      .id;
    const genders = res.data.map(el => ({
      value: el.id,
      key: el.id,
      text: el.name
    }));
    return {
      genders,
      defaultMaleGenderId,
      defaultFemaleGenderId
    };
  });

const fetchCountriesWithDefaultKey = () =>
  axios.get('http://localhost:8081/countries').then(res => {
    const bihId = res.data.find(el => el._key === 'bih').id;
    const countries = res.data.map(el => ({
      value: el.id,
      key: el.id,
      text: el.name
    }));
    return { countries, bihId };
  });

const fetchEntitiesWithDefaultKey = () =>
  axios.get('http://localhost:8081/entities').then(res => {
    const fbihId = res.data.find(el => el._key === 'fbih').id;
    const rsId = res.data.find(el => el._key === 'rs').id;
    const bdId = res.data.find(el => el._key === 'bd').id;
    const entities = res.data.map(el => ({
      value: el.id,
      key: el.id,
      text: el.name
    }));
    return {
      entities,
      fbihId,
      rsId,
      bdId
    };
  });

const fetchCantons = () =>
  axios.get('http://localhost:8081/cantons').then(dropdownMapper);

const fetchMunicipalities = () =>
  axios.get('http://localhost:8081/municipalities').then(res => {
    const municipalities = res.data.map(el => ({
      value: el.id,
      key: el.id,
      text: el.name,
      canton: el.canton
    }));
    return {
      municipalities
    };
  });

const fetchNationalities = () =>
  axios.get('http://localhost:8081/nationalities').then(dropdownMapper);

const fetchBloodTypes = () =>
  axios.get('http://localhost:8081/blood-types').then(dropdownMapper);

const formatPlace = (place, state, withoutStreet) => {
  if (place.place === null && place.address === null) return null;

  let newPlace = {
    name: place.place,
    id: place.placeId ? place.placeId : null,
    country: { id: place.country }
  };

  if (place.country === state.bihId) {
    newPlace = {
      ...newPlace,
      entity: { id: place.entity }
    };
  }
  if (place.entity === state.fbihId) {
    newPlace = {
      ...newPlace,
      canton: { id: place.canton },
      municipality: place.municipality ? { id: place.municipality } : null
    };
  }

  if (place.entity === state.rsId) {
    newPlace = {
      ...newPlace,
      municipality: place.municipality ? { id: place.municipality } : null
    };
  }

  if (withoutStreet) {
    return {
      ...newPlace,
      id: place.id ? place.id : null,
      optLock: place.optLock ? place.optLock : null
    };
  }
  return {
    street: place.address,
    place: newPlace,
    id: place.id ? place.id : null,
    optLock: place.optLock ? place.optLock : null
  };
};

const formatAndValidateForRequest = state => {
  // add validation!
  let dateOfBirth;
  try {
    dateOfBirth = moment(state.dateOfBirth).format();
  } catch (e) {
    dateOfBirth = null;
  }
  //mother
  let activityStatusM = {
    status: { id: state.activityStatusTypeM }
  };
  if (activityStatusM.status.id === null) activityStatusM = null;

  if (state.occupationStatusM) {
    activityStatusM = {
      ...activityStatusM,
      occupationStatus: { id: state.occupationStatusM }
    };
  }
  if (state.occupationM) {
    activityStatusM = { ...activityStatusM, occupation: state.occupationM };
  }

  //father
  let activityStatusF = {
    status: { id: state.activityStatusTypeF }
  };
  if (activityStatusF.status.id === null) activityStatusF = null;

  if (state.occupationStatusF) {
    activityStatusF = {
      ...activityStatusF,
      occupationStatus: { id: state.occupationStatusF }
    };
  }
  if (state.occupationF) {
    activityStatusF = { ...activityStatusF, occupation: state.occupationF };
  }

  let person = {
    id: state.studentId,
    firstName: state.firstName,
    lastName: state.lastName,
    jmbg: state.jmbg,
    gender: {
      id: state.gender
    },
    dateOfBirth,
    mother:
      state.motherFirstName || state.motherLastName
        ? {
            firstName: state.motherFirstName,
            lastName: state.motherLastName,
            gender: {
              id: state.motherGender
            },
            id: state.motherId ? state.motherId : null
          }
        : null,
    father:
      state.fatherFirstName || state.fatherLastName
        ? {
            firstName: state.fatherFirstName,
            lastName: state.fatherLastName,
            gender: {
              id: state.fatherGender
            },
            id: state.fatherId ? state.fatherId : null
          }
        : null
  };

  const placeOfBirth = formatPlace(state.placeOfBirth, state, true);
  const residence = formatPlace(state.placeOfResidence, state);

  person = {
    ...person,
    placeOfBirth,
    residence
  };

  let contacts = state.contacts.map(contact => ({
    value: contact.value,
    contactType: {
      id: contact.contactTypeId
    },
    id: contact.id ? contact.id : null,
    optLock: contact.optLock ? contact.optLock : null
  }));

  let personObj = {
    personDTO: {
      person,
      contacts
    }
  };

  personObj = {
    ...personObj
  };

  return {
    valid: true,
    errors: {},
    data: personObj
  };
};

const fetchScoutGroups = () =>
  axios.get('http://localhost:8081/scout-groups').then(dropdownMapper);

const fetchPersonInfo = memberId =>
  axios.get(`http://localhost:8081/member/${memberId}/`).then(res => {
    return {
      personData: res.data
    };
  });

const fetchLeadersForScoutGroup = scoutGroupId =>
  axios
    .get(`http://localhost:8081/member/leaders/${scoutGroupId}/`)
    .then(res => {
      const leaders = res.data.map(el => ({
        value: el.id,
        key: el.id,
        text: el.person.firstName + ' ' + el.person.lastName
      }));
      return {
        leaders
      };
    });

const fetchMembersForUnit = (scoutGroupId, unitId) =>
  axios
    .get(
      `http://localhost:8081/member/by-scout-group/${scoutGroupId}/${unitId}/`
    )
    .then(res => {
      const members = res.data.map(el => ({
        value: el.memberId,
        key: el.memberId,
        text: el.personDTO.firstName + ' ' + el.personDTO.lastName
      }));
      return {
        members
      };
    });

const fetchUnitTypes = () =>
  axios.get('http://localhost:8081/unit-types').then(dropdownMapper);

export default {
  fetchPersonalContacts,
  fetchGendersWithDefaultKeys,
  fetchEntitiesWithDefaultKey,
  formatAndValidateForRequest,
  fetchCantons,
  fetchMunicipalities,
  fetchCountriesWithDefaultKey,
  fetchNationalities,
  fetchBloodTypes,
  fetchScoutGroups,
  fetchPersonInfo,
  fetchLeadersForScoutGroup,
  fetchUnitTypes,
  fetchMembersForUnit
};
