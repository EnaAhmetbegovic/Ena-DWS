import React, { Component } from 'react';
import TableContainer from '@material-ui/core/TableContainer';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import axios from 'axios';
import { Redirect } from 'react-router';

class MemberList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showPersonData: false,
      rows: [
        {
          id: null,
          firstName: null,
          lastName: null,
          fathersFullName: null,
          category: null
        }
      ]
    };

    this.getMember = this.getMember.bind(this);
  }

  getMember(memberId) {
    localStorage.setItem('memberId', JSON.stringify(memberId));
    this.setState({ showPersonData: true });
  }

  componentDidMount() {
    let scoutGroup = 5;
    axios
      .get(`http://localhost:8081/member/by-scout-group/${scoutGroup}/`)
      .then(res => {
        let rows = res.data.map(e => ({
          id: e.memberId,
          firstName: e.personDTO.firstName,
          lastName: e.personDTO.lastName,
          fathersFullName: e.personDTO.fathersFullName,
          category: e.category !== null ? e.category.name : '/'
        }));

        this.setState({ rows });
      })
      .catch(err => {
        this.setState({
          rows: [
            {
              id: null,
              firstName: 'Prazno',
              lastName: 'Prazno',
              fathersFullName: 'Prazno',
              category: 'Prazno'
            }
          ]
        });
        console.log('Error: ', err);
      });
  }

  render() {
    if (this.state.showPersonData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/podaci-o-clanu'
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
                <TableCell align="left">Kategorija</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.rows.map((row, index) => (
                <TableRow
                  key={row.id}
                  onClick={() => {
                    this.getMember(row.id);
                  }}
                  hover>
                  <TableCell align="left">{index + 1}.</TableCell>
                  <TableCell component="th" scope="row">
                    {row.firstName}
                  </TableCell>
                  <TableCell align="left">{row.fathersFullName}</TableCell>
                  <TableCell align="left">{row.lastName}</TableCell>
                  <TableCell align="left">{row.category}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    );
  }
}

export default MemberList;
