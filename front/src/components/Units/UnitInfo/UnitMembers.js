import React from 'react';
import { Table } from 'semantic-ui-react';

import Paper from '@material-ui/core/Paper';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import TableContainer from '@material-ui/core/TableContainer';
import moment from 'moment';

export default class UnitMembers extends React.Component {
  render() {
    const { unitInfoData } = this.props;
    return (
      <div>
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="left" width={1}>
                  #
                </TableCell>
                <TableCell align="left">Ime</TableCell>
                <TableCell align="left">Prezime</TableCell>
                <TableCell align="left">Postao član</TableCell>
                <TableCell align="left">Prestao biti član</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {unitInfoData.map((row, index) => (
                <TableRow key={row.id}>
                  <TableCell align="left">{index + 1}.</TableCell>
                  <TableCell component="th" scope="row">
                    {row.member.person.firstName}
                  </TableCell>
                  <TableCell align="left">{row.member.person.lastName}</TableCell>
                  <TableCell align="left">
                    {moment(row.joinedUnit).format('D. M. YYYY.')}
                  </TableCell>
                  <TableCell align="left">
                    {row.leftUnit !== null
                      ? moment(row.leftUnit).format('D. M. YYYY.')
                      : '/'}
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
