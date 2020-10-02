import React from 'react';
import { Table } from 'semantic-ui-react';

const SimpleTwoColumnTableRow = props => (
  <Table.Row disabled={props.disabled}>
    <Table.Cell textAlign="right" collapsing>
      <b>{props.name}</b>
    </Table.Cell>
    <Table.Cell
    //verticalAlign="middle"
    >
      {props.value}
    </Table.Cell>
  </Table.Row>
);

export default SimpleTwoColumnTableRow;
