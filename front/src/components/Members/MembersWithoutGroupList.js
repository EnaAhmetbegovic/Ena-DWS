import React, { Component } from 'react';
import TableContainer from '@material-ui/core/TableContainer';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';

import axios from 'axios';
import { Redirect } from 'react-router';
import TableBody from '@material-ui/core/TableBody';

class MembersWithoutGroupList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showPersonData: false,
      rows: [
        {
          id: null,
          firstName: null,
          lastName: null,
          fathersFirstName: null
        }
      ]
    };

    this.enrollMember = this.enrollMember.bind(this);
  }

  enrollMember(memberId) {
    localStorage.setItem('memberId', JSON.stringify(memberId));
    this.setState({ showPersonData: true });
  }

  componentDidMount() {
    axios
      .get('http://localhost:8081/member/without-group/')
      .then(
        res => {
        // let rows = res.data.map(e => ({
        //   id: e.id,
        //   firstName: e.person.firstName,
        //   fathersFirstName: e.person.father.firstName,
        //   lastName: e.person.lastName
        // }));
        // this.setState({ rows });
      }
      )
      .catch(err => {
        // this.setState({
        //   rows: [
        //     {
        //       id: null,
        //       firstName: 'Prazno',
        //       fathersFirstName: 'Prazno',
        //       lastName: 'Prazno'
        //     }
        //   ]
        // });
        console.log('Error: ', err);
      });
  }

  render() {
    if (this.state.showPersonData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/upisi-novog-clana'
          }}
        />
      );
    }

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
                <TableCell align="left">Ime oca</TableCell>
                <TableCell align="left">Prezime</TableCell>
              </TableRow>
            </TableHead>
            {/*<TableBody>*/}
            {/*  {this.state.rows.map((row, index) => (*/}
            {/*    <TableRow*/}
            {/*      key={row.id}*/}
            {/*      onClick={() => {*/}
            {/*        this.enrollMember(row.id);*/}
            {/*      }}*/}
            {/*      hover>*/}
            {/*      <TableCell align="left">{index + 1}.</TableCell>*/}
            {/*      <TableCell component="th" scope="row">*/}
            {/*        {row.firstName}*/}
            {/*      </TableCell>*/}
            {/*      <TableCell component="th" scope="row">*/}
            {/*        {row.fathersFirstName}*/}
            {/*      </TableCell>*/}
            {/*      <TableCell align="left">{row.lastName}</TableCell>*/}
            {/*    </TableRow>*/}
            {/*  ))}*/}
            {/*</TableBody>*/}
          </Table>
        </TableContainer>
      </div>
    );
  }
}

export default MembersWithoutGroupList;
