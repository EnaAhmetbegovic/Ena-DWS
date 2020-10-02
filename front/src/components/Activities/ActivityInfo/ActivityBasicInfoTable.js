import React from 'react';
import { Table } from 'semantic-ui-react';
import moment from 'moment';
import SimpleTwoColumnTableRow from '../../Common/SimpleTwoColumnTableRow';

export default class ActivityBasicInfoTable extends React.Component {
  render() {
    const { activityInfoData } = this.props;
    return (
      <Table celled size="small" unstackable compact>
        <Table.Header>
          <Table.Row></Table.Row>
        </Table.Header>
        <Table.Body>
          <SimpleTwoColumnTableRow
            name="Tip aktivnosti"
            keyName="activity"
            value={activityInfoData.activityType.name}
          />

          <SimpleTwoColumnTableRow
            name="Naziv"
            keyName="name"
            value={activityInfoData.name}
          />

          <SimpleTwoColumnTableRow
            name="Organizator"
            keyName="organizer"
            value={activityInfoData.organizer.scoutGroup.name}
          />

          <SimpleTwoColumnTableRow
            name="Datum početka"
            keyName="startDate"
            value={moment(activityInfoData.startDate).format('D. M. YYYY.')}
          />

          <SimpleTwoColumnTableRow
            name="Datum završetka"
            keyName="endDate"
            value={moment(activityInfoData.endDate).format('D. M. YYYY.')}
          />

          <SimpleTwoColumnTableRow
            name="Načelnik akcije"
            keyName="chief"
            value={
              activityInfoData.chief.person.firstName +
              ' ' +
              activityInfoData.chief.person.lastName
            }
          />

          <SimpleTwoColumnTableRow
            name="Starješina akcije"
            keyName="head"
            value={
              activityInfoData.head.person.firstName +
              ' ' +
              activityInfoData.head.person.lastName
            }
          />

          <SimpleTwoColumnTableRow
            name="Mjesto održavanja"
            keyName="place"
            value={activityInfoData.place}
          />
        </Table.Body>
      </Table>
    );
  }
}
