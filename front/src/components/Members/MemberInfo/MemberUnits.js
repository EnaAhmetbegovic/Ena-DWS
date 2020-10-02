import React from 'react';
import { Table } from 'semantic-ui-react';

import Paper from '@material-ui/core/Paper';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import TableContainer from '@material-ui/core/TableContainer';
import moment from 'moment';

export default class MemberUnits extends React.Component {
  render() {
    const { personInfoData } = this.props;
    return (
      <div>
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="left" width={1}>
                  #
                </TableCell>
                <TableCell align="left">Jedinica</TableCell>
                <TableCell align="left">Naziv jedinice</TableCell>
                <TableCell align="left">Osnovana</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {personInfoData.personData.units.map((row, index) => (
                <TableRow key={row.id}>
                  <TableCell align="left">{index + 1}.</TableCell>
                  <TableCell component="th" scope="row">
                    {row.unit.name}
                  </TableCell>
                  <TableCell align="left">{row.unitName}</TableCell>
                  <TableCell align="left">
                    {moment(row.founded).format('D. M. YYYY.')}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    );
  }
}
