import React from 'react';
import { Table } from 'semantic-ui-react';
import SimpleTwoColumnTableRow from '../../Common/SimpleTwoColumnTableRow';
import moment from 'moment';

export default class UnitBasicInfoTable extends React.Component {
  render() {
    const { unitInfoData } = this.props;
    return (
      <Table celled size="small" unstackable compact>
        <Table.Header>
          <Table.Row></Table.Row>
        </Table.Header>
        <Table.Body>
          <SimpleTwoColumnTableRow
            name="Tip jedinice"
            keyName="unit"
            value={unitInfoData.unit.name}
          />

          <SimpleTwoColumnTableRow
            name="Naziv"
            keyName="unitName"
            value={unitInfoData.unitName}
          />

          <SimpleTwoColumnTableRow
            name="Vodnik/Predvodnik"
            keyName="leader"
            value={
              unitInfoData.leader.person.firstName +
              ' ' +
              unitInfoData.leader.person.lastName
            }
          />

          <SimpleTwoColumnTableRow
            name="Osnovana"
            keyName="founded"
            value={moment(unitInfoData.founded).format('D. M. YYYY.')}
          />
        </Table.Body>
      </Table>
    );
  }
}
