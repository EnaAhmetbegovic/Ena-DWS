import React, { Component } from 'react';

import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';

import InputLabel from '@material-ui/core/InputLabel';

import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import axios from 'axios';

import request from '../../utils/request';

import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

import Box from '@material-ui/core/Box';
import { Redirect } from 'react-router';

export default class AddNewMember extends Component {
  constructor(props) {
    super(props);
    this.state = {
      addMemberData: false,
      genders: [],
      nationalities: [],
      municipalities: [],
      cantons: [],
      entities: [],
      countries: [],
      bloodTypes: [],
      contactTypes: [],

      filteredEntitiesBirthPlace: [],
      filteredCantonsBirthPlace: [],
      filteredMunicipalitiesBirthPlace: [],
      filteredEntitiesResidence: [],
      filteredCantonsResidence: [],
      filteredMunicipalitiesResidence: [],

      bihId: null,
      fbihId: null,
      rsId: null,
      bdId: null,

      birthEntityEnabled: true,
      birthCantonEnabled: true,
      birthMunicipalityEnabled: true,
      residenceEntityEnabled: true,
      residenceCantonEnabled: true,
      residenceMunicipalityEnabled: true,

      // FORM DATA
      jmbg: '',
      firstName: '',
      lastName: '',
      dateOfBirth: new Date(),
      genderId: null,
      nationalityId: null,
      bloodTypeId: null,

      // parents
      fatherFirstName: '',
      fatherLastName: '',
      fatherGenderId: null,

      motherFirstName: '',
      motherLastName: '',
      motherGenderId: null,

      // contacts
      contacts: [],
      selectedContactTypeId: null,
      selectedContactType: '',
      inputContactType: '',

      // residence
      residenceStreet: '',
      residenceCountryId: null,
      residenceEntityId: null,
      residenceCantonId: null,
      residenceMunicipalityId: null,
      residencePlaceName: '',

      // place of birth
      placeOfBirthCountryId: null,
      placeOfBirthEntityId: null,
      placeOfBirthCantonId: null,
      placeOfBirthMunicipalityId: null,
      placeOfBirthPlaceName: '',
      placeOfBirthAddress: ''
    };

    this.addMember = this.addMember.bind(this);
    this.handleDateOfBirthChange = this.handleDateOfBirthChange.bind(this);
    this.handlePlaceChange = this.handlePlaceChange.bind(this);
    this.addContact = this.addContact.bind(this);
  }

  addMember() {
    let obj = {
      person: {
        // personal info
        firstName: this.state.firstName,
        lastName: this.state.lastName,
        gender: {
          id: this.state.genderId
        },
        jmbg: this.state.jmbg,

        // parent info
        father: {
          firstName: this.state.fatherFirstName,
          lastName: this.state.fatherLastName,
          gender: {
            id: this.state.fatherGenderId
          }
        },
        mother: {
          firstName: this.state.firstName,
          lastName: this.state.lastName,
          gender: {
            id: this.state.motherGenderId
          }
        },

        dateOfBirth: this.state.dateOfBirth,
        placeOfBirth: {
          name: this.state.placeOfBirthPlaceName,
          country: { id: this.state.placeOfBirthCountryId },
          entity: this.state.placeOfBirthEntityId
            ? { id: this.state.placeOfBirthEntityId }
            : null,
          municipality: this.state.placeOfBirthMunicipalityId
            ? { id: this.state.placeOfBirthMunicipalityId }
            : null,
          canton: this.state.placeOfBirthCantonId
            ? { id: this.state.placeOfBirthCantonId }
            : null
        },

        nationality: {
          id: this.state.nationalityId
        },

        residence: {
          place: {
            name: this.state.residencePlaceName,
            country: { id: this.state.residenceCountryId },
            entity: this.state.residenceEntityId
              ? { id: this.state.residenceEntityId }
              : null,
            municipality: this.state.residenceMunicipalityId
              ? { id: this.state.residenceMunicipalityId }
              : null,
            canton: this.state.residenceCantonId
              ? { id: this.state.residenceCantonId }
              : null
          },
          street: this.state.residenceStreet
        },

        bloodType: {
          id: this.state.bloodTypeId
        },
        contacts: this.state.contacts
      },
      contacts: this.state.contacts
    };

    axios
      .post('http://localhost:8081/member/add-person/', { ...obj })
      .then(res => {
        this.setState({ person: '' });
        localStorage.setItem('memberId', JSON.stringify(res.data.id));
        this.setState({ addMemberData: true });
      });
  }

  addContact() {
    let newContact = {
      person: null,
      value: this.state.inputContactType,
      contactType: {
        id: this.state.selectedContactType[0].value,
        name: this.state.selectedContactType[0].text
      }
    };

    this.setState(previousState => ({
      contacts: [...previousState.contacts, newContact]
    }));

    this.setState({ inputContactType: '' });
  }

  handleDateOfBirthChange(date) {
    this.setState({ dateOfBirth: date });
  }

  handlePlaceChange(name, value, type) {
    if (name === 'country') {
      if (type === 'birthPlace') {
        this.setState({
          placeOfBirthCountryId: value
        });

        if (value === this.state.bihId) {
          this.setState({
            filteredEntitiesBirthPlace: this.state.entities,
            birthEntityEnabled: false
          });
        } else {
          this.setState({
            birthEntityEnabled: true,
            birthCantonEnabled: true,
            birthMunicipalityEnabled: true,
            placeOfBirthEntityId: null,
            placeOfBirthCantonId: null,
            placeOfBirthMunicipalityId: null
          });
        }
      } else if (type === 'residence') {
        this.setState({
          residenceCountryId: value
        });

        if (value === this.state.bihId) {
          this.setState({
            filteredEntitiesResidence: this.state.entities,
            residenceEntityEnabled: false
          });
        } else {
          this.setState({
            residenceEntityEnabled: true,
            residenceCantonEnabled: true,
            residenceMunicipalityEnabled: true,
            residenceEntityId: null,
            residenceCantonId: null,
            residenceMunicipalityId: null
          });
        }
      }
    } else if (name === 'entity') {
      if (type === 'birthPlace') {
        this.setState({
          placeOfBirthEntityId: value
        });

        if (value === this.state.fbihId) {
          this.setState({
            filteredCantonsBirthPlace: this.state.cantons,
            birthCantonEnabled: false
          });
        } else if (value === this.state.rsId) {
          const filterNull = this.state.municipalities.municipalities.filter(
            el => el.canton == null
          );

          this.setState({
            filteredMunicipalitiesBirthPlace: filterNull,
            birthMunicipalityEnabled: false
          });
        }
      } else if (type === 'residence') {
        this.setState({
          residenceEntityId: value
        });

        if (value === this.state.fbihId) {
          this.setState({
            filteredCantonsResidence: this.state.cantons,
            residenceCantonEnabled: false
          });
        } else if (value === this.state.rsId) {
          const filterNull = this.state.municipalities.municipalities.filter(
            el => el.canton == null
          );

          this.setState({
            filteredMunicipalitiesResidence: filterNull,
            residenceMunicipalityEnabled: false
          });
        }
      }
    } else if (name === 'canton') {
      if (type === 'birthPlace') {
        this.setState({
          placeOfBirthCantonId: value
        });

        const filterNull = this.state.municipalities.municipalities.filter(
          el => el.canton !== null
        );

        const filterCantons = filterNull.filter(el => el.canton.id === value);

        this.setState({
          filteredMunicipalitiesBirthPlace: filterCantons,
          birthMunicipalityEnabled: false
        });
      } else if (type === 'residence') {
        this.setState({
          residenceCantonId: value
        });

        const filterNull = this.state.municipalities.municipalities.filter(
          el => el.canton !== null
        );

        const filterCantons = filterNull.filter(el => el.canton.id === value);

        this.setState({
          filteredMunicipalitiesResidence: filterCantons,
          residenceMunicipalityEnabled: false
        });
      }
    } else if (name === 'municipality') {
      if (type === 'birthPlace') {
        this.setState({
          placeOfBirthMunicipalityId: value
        });
      } else if (type === 'residence') {
        this.setState({
          residenceMunicipalityId: value
        });
      }
    }
  }

  componentDidMount() {
    request
      .fetchGendersWithDefaultKeys()
      .then(({ genders, defaultMaleGenderId, defaultFemaleGenderId }) => {
        this.setState({
          genders,
          fatherGenderId: defaultMaleGenderId,
          motherGenderId: defaultFemaleGenderId
        });
      })
      .catch(err => console.log(err));

    request
      .fetchCountriesWithDefaultKey()
      .then(({ countries, bihId }) => this.setState({ countries, bihId }));

    request
      .fetchEntitiesWithDefaultKey()
      .then(({ entities, fbihId, rsId }) =>
        this.setState({ entities, fbihId, rsId })
      );

    request.fetchCantons().then(cantons => this.setState({ cantons }));

    request
      .fetchMunicipalities()
      .then(municipalities => this.setState({ municipalities }));

    request
      .fetchNationalities()
      .then(nationalities => this.setState({ nationalities }));

    request.fetchBloodTypes().then(bloodTypes => this.setState({ bloodTypes }));

    request
      .fetchPersonalContacts()
      .then(contactTypes => this.setState({ contactTypes }));
  }

  render() {
    if (this.state.addMemberData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/upisi-novog-clana'
          }}
        />
      );
    }

    const {
      genders,
      countries,
      filteredEntitiesBirthPlace,
      filteredCantonsBirthPlace,
      filteredMunicipalitiesBirthPlace,
      filteredEntitiesResidence,
      filteredCantonsResidence,
      filteredMunicipalitiesResidence,
      nationalities,
      bloodTypes,
      contactTypes
    } = this.state;

    return (
      <div>
        <Container maxWidth="sm">
          <Typography
            component="div"
            style={{
              backgroundColor: '#f8f8f7',
              height: '100%',
              padding: '10px'
            }}>
            <form noValidate autoComplete="off">
              <Box>
                <TextField
                  id="firstName"
                  label="Ime"
                  value={this.state.firstName}
                  onChange={e => {
                    this.setState({ firstName: e.target.value }, () =>
                      console.log(this.state.firstName)
                    );
                  }}
                />
                <br />

                <TextField
                  id="lastName"
                  label="Prezime"
                  value={this.state.lastName}
                  onChange={e => {
                    this.setState({ lastName: e.target.value });
                    console.log(this.state.lastName);
                  }}
                />
                <br />

                <TextField
                  id="jmbg"
                  label="JMBG"
                  value={this.state.jmbg}
                  onChange={e => {
                    this.setState({ jmbg: e.target.value });
                    console.log(this.state.jmbg);
                  }}
                />
                <br />
                <br />

                <DatePicker
                  selected={this.state.dateOfBirth}
                  onChange={this.handleDateOfBirthChange}
                  dateFormat="dd/MM/yyyy"
                />
                <br />

                <FormControl required>
                  <InputLabel>Spol</InputLabel>
                  <Select
                    // disabled={true}
                    onChange={e => {
                      this.setState(
                        {
                          genderId: e.target.value
                        },
                        () => console.log(this.state.genderId)
                      );
                    }}>
                    {genders.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />
                <br />

                <p>Mjesto rođenja</p>

                <FormControl required>
                  <InputLabel>Država</InputLabel>
                  <Select
                    onChange={e => {
                      this.handlePlaceChange(
                        'country',
                        e.target.value,
                        'birthPlace'
                      );
                    }}>
                    {countries.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Entitet</InputLabel>
                  <Select
                    disabled={this.state.birthEntityEnabled}
                    onChange={e => {
                      this.handlePlaceChange(
                        'entity',
                        e.target.value,
                        'birthPlace'
                      );
                    }}>
                    {filteredEntitiesBirthPlace.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Kanton</InputLabel>
                  <Select
                    disabled={this.state.birthCantonEnabled}
                    onChange={e => {
                      this.handlePlaceChange(
                        'canton',
                        e.target.value,
                        'birthPlace'
                      );
                    }}>
                    {filteredCantonsBirthPlace.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Općina</InputLabel>
                  <Select
                    disabled={this.state.birthMunicipalityEnabled}
                    onChange={e => {
                      this.handlePlaceChange(
                        'municipality',
                        e.target.value,
                        'birthPlace'
                      );
                    }}>
                    {filteredMunicipalitiesBirthPlace.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <TextField
                  id="placeOfBirth"
                  label="Mjesto"
                  value={this.state.placeOfBirthPlaceName}
                  onChange={e => {
                    this.setState({
                      placeOfBirthPlaceName: e.target.value
                    });
                    console.log(this.state.placeOfBirthPlaceName);
                  }}
                />
                <br />
                <br />

                <FormControl required>
                  <InputLabel>Nacionalnost</InputLabel>
                  <Select
                    onChange={e => {
                      this.setState(
                        {
                          nationalityId: e.target.value
                        },
                        () => console.log(this.state.nationalityId)
                      );
                    }}>
                    {nationalities.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />
                <br />

                <p>Prebivalište</p>

                <FormControl required>
                  <InputLabel>Država</InputLabel>
                  <Select
                    onChange={e => {
                      this.handlePlaceChange(
                        'country',
                        e.target.value,
                        'residence'
                      );
                    }}>
                    {countries.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Entitet</InputLabel>
                  <Select
                    disabled={this.state.residenceEntityEnabled}
                    onChange={e => {
                      this.handlePlaceChange(
                        'entity',
                        e.target.value,
                        'residence'
                      );
                    }}>
                    {filteredEntitiesResidence.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Kanton</InputLabel>
                  <Select
                    disabled={this.state.residenceCantonEnabled}
                    onChange={e => {
                      this.handlePlaceChange(
                        'canton',
                        e.target.value,
                        'residence'
                      );
                    }}>
                    {filteredCantonsResidence.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Općina</InputLabel>
                  <Select
                    disabled={this.state.residenceMunicipalityEnabled}
                    onChange={e => {
                      this.handlePlaceChange(
                        'municipality',
                        e.target.value,
                        'residence'
                      );
                    }}>
                    {filteredMunicipalitiesResidence.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <TextField
                  id="residence"
                  label="Mjesto"
                  value={this.state.residencePlaceName}
                  onChange={e => {
                    this.setState({
                      residencePlaceName: e.target.value
                    });
                    console.log(this.state.residencePlaceName);
                  }}
                />
                <br />

                <TextField
                  id="street"
                  label="Adresa"
                  value={this.state.residenceStreet}
                  onChange={e => {
                    this.setState({
                      residenceStreet: e.target.value
                    });
                    console.log(this.state.residenceStreet);
                  }}
                />
                <br />
                <br />

                <FormControl required>
                  <InputLabel>Krvna grupa</InputLabel>
                  <Select
                    onChange={e => {
                      this.setState(
                        {
                          bloodTypeId: e.target.value
                        },
                        () => console.log(this.state.bloodTypeId)
                      );
                    }}>
                    {bloodTypes.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />
                <br />

                <table>
                  {this.state.contacts.map(contact => (
                    <tr>
                      <td>{contact.contactType.name}</td>
                      <td>{contact.value}</td>
                    </tr>
                  ))}
                </table>

                <FormControl>
                  <InputLabel>Tip kontakta</InputLabel>
                  <Select
                    onChange={e => {
                      this.setState({
                        selectedContactType: contactTypes.filter(
                          ct => ct.value === e.target.value
                        )
                      });
                    }}>
                    {contactTypes.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <TextField
                  id="contact"
                  label="Vrijednost"
                  value={this.state.inputContactType}
                  onChange={e => {
                    this.setState({
                      inputContactType: e.target.value
                    });
                  }}
                />

                <Button onClick={this.addContact}>Dodaj</Button>
                <br />
                <br />

                <TextField
                  id="fatherFirstName"
                  label="Ime oca"
                  value={this.state.fatherFirstName}
                  onChange={e => {
                    this.setState({ fatherFirstName: e.target.value });
                    console.log(this.state.fatherFirstName);
                  }}
                />
                <br />

                <TextField
                  id="fatherLastName"
                  label="Prezime oca"
                  value={this.state.fatherLastName}
                  onChange={e => {
                    this.setState({ fatherLastName: e.target.value });
                    console.log(this.state.fatherLastName);
                  }}
                />

                <br />

                <TextField
                  id="motherFirstName"
                  label="Ime majke"
                  value={this.state.motherFirstName}
                  onChange={e => {
                    this.setState({ motherFirstName: e.target.value });
                    console.log(this.state.motherFirstName);
                  }}
                />
                <br />

                <TextField
                  id="motherLastName"
                  label="Prezime majke"
                  value={this.state.motherLastName}
                  onChange={e => {
                    this.setState({ motherLastName: e.target.value });
                    console.log(this.state.motherLastName);
                  }}
                />

                {/*Dodati kontakt roditelja*/}

                <br />
                <br />

                <Button onClick={this.addMember}>Unesi člana</Button>
              </Box>
            </form>
          </Typography>
        </Container>{' '}
      </div>
    );
  }
}
