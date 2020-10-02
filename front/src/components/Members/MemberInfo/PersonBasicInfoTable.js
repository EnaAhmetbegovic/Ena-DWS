import React from 'react';
import { Table } from 'semantic-ui-react';
import SimpleTwoColumnTableRow from '../../Common/SimpleTwoColumnTableRow';
import moment from 'moment';

export default class PersonBasicInfoTable extends React.Component {
  render() {
    const { personInfoData } = this.props;
    return (
      <Table celled size="small" unstackable compact>
        <Table.Header>
          <Table.Row></Table.Row>
        </Table.Header>
        <Table.Body>
          <SimpleTwoColumnTableRow
            name="JMBG"
            keyName="jmbg"
            value={personInfoData.personData.personDTO.jmbg}
          />

          <SimpleTwoColumnTableRow
            name="Ime"
            keyName="firstName"
            value={personInfoData.personData.personDTO.firstName}
          />

          <SimpleTwoColumnTableRow
            name="Prezime"
            keyName="lastName"
            value={personInfoData.personData.personDTO.lastName}
          />

          <SimpleTwoColumnTableRow
            name="Spol"
            keyName="gender"
            value={personInfoData.personData.personDTO.gender.name}
          />

          <SimpleTwoColumnTableRow
            name="Datum rođenja"
            keyName="dateOfBirth"
            value={moment(
              personInfoData.personData.personDTO.dateOfBirth
            ).format('D. M. YYYY.')}
          />

          <SimpleTwoColumnTableRow
            name="Mjesto rođenja"
            keyName="placeOfBirth"
            value={
              (personInfoData.personData.personDTO.placeOfBirth.country !== null
                ? personInfoData.personData.personDTO.placeOfBirth.country.name
                : '') +
              (personInfoData.personData.personDTO.placeOfBirth.entity !== null
                ? ', ' +
                  personInfoData.personData.personDTO.placeOfBirth.entity.name
                : '') +
              (personInfoData.personData.personDTO.placeOfBirth.canton !== null
                ? ', ' +
                  personInfoData.personData.personDTO.placeOfBirth.canton.name
                : '') +
              (personInfoData.personData.personDTO.placeOfBirth.municipality !==
              null
                ? ', ' +
                  personInfoData.personData.personDTO.placeOfBirth.municipality
                    .name
                : '') +
              ', ' +
              personInfoData.personData.personDTO.placeOfBirth.name
            }
          />

          <SimpleTwoColumnTableRow
            name="Ime oca"
            keyName="father"
            value={personInfoData.personData.personDTO.fathersFullName}
          />

          <SimpleTwoColumnTableRow
            name="Ime majke"
            keyName="mother"
            value={personInfoData.personData.personDTO.mothersFullName}
          />

          <SimpleTwoColumnTableRow
            name="Mjesto prebivališta"
            keyName="residence"
            value={
              (personInfoData.personData.personDTO.residence.place.country !==
              null
                ? personInfoData.personData.personDTO.residence.place.country
                    .name
                : '') +
              (personInfoData.personData.personDTO.residence.place.entity !==
              null
                ? ', ' +
                  personInfoData.personData.personDTO.residence.place.entity
                    .name
                : '') +
              (personInfoData.personData.personDTO.residence.place.canton !==
              null
                ? ', ' +
                  personInfoData.personData.personDTO.residence.place.canton
                    .name
                : '') +
              (personInfoData.personData.personDTO.residence.place
                .municipality !== null
                ? ', ' +
                  personInfoData.personData.personDTO.residence.place
                    .municipality.name
                : '') +
              ', ' +
              personInfoData.personData.personDTO.residence.place.name +
              ', ' +
              personInfoData.personData.personDTO.residence.street
            }
          />

          <SimpleTwoColumnTableRow
            name="Nacionalnost"
            keyName="nationality"
            value={personInfoData.personData.personDTO.nationality.name}
          />

          <SimpleTwoColumnTableRow
            name="Krvna grupa"
            keyName="currentResidence"
            value={personInfoData.personData.personDTO.bloodType.name}
          />
        </Table.Body>
      </Table>
    );
  }
}
