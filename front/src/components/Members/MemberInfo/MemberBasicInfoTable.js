import React from 'react';
import { Table } from 'semantic-ui-react';
import SimpleTwoColumnTableRow from '../../Common/SimpleTwoColumnTableRow';
import moment from 'moment';

export default class MemberBasicInfoTable extends React.Component {
  render() {
    const { personInfoData } = this.props;
    return (
      <Table celled size="small" unstackable compact>
        <Table.Header>
          <Table.Row></Table.Row>
        </Table.Header>
        <Table.Body>
          <SimpleTwoColumnTableRow
            name="Odred izviđača"
            keyName="scoutGroup"
            value={personInfoData.personData.scoutGroup.scoutGroup.name}
          />

          <SimpleTwoColumnTableRow
            name="Član od"
            keyName="memberSince"
            value={moment(personInfoData.personData.memberSince).format(
              'D. M. YYYY.'
            )}
          />

          <SimpleTwoColumnTableRow
            name="Broj članske knjižice"
            keyName="idCardNumber"
            value={personInfoData.personData.idCardNumber}
          />

          <SimpleTwoColumnTableRow
            name="Redni broj člana"
            keyName="recordNumber"
            value={personInfoData.personData.recordNumber}
          />

          <SimpleTwoColumnTableRow
            name="Kategorija"
            keyName="category"
            value={
              personInfoData.personData.category !== null
                ? personInfoData.personData.category.name
                : ''
            }
          />
        </Table.Body>
      </Table>
    );
  }
}
