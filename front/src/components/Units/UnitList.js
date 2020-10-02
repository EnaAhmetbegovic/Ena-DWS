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
import moment from 'moment';
import Button from '@material-ui/core/Button';

class UnitList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showUnitData: false,
      addNewUnit: false,
      rows: [
        {
          id: null,
          unitType: null,
          founded: null,
          unitName: null,
          leaderFullName: null
        }
      ]
    };

    this.addUnit = this.addUnit.bind(this);
    this.getUnit = this.getUnit.bind(this);
  }

  addUnit() {
    this.setState({ addNewUnit: true });
  }

  getUnit(unitId) {
    localStorage.setItem('unitId', JSON.stringify(unitId));
    this.setState({ showUnitData: true });
  }

  componentDidMount() {
    let scoutGroup = 5;
    axios
      .get(`http://localhost:8081/unit/by-scout-group/${scoutGroup}/`)
      .then(res => {
        let rows = res.data.map(e => ({
          id: e.id,
          unitType: e.unit.name,
          founded: moment(e.founded).format('D. M. YYYY.'),
          unitName: e.unitName,
          leaderFullName: e.leader
            ? e.leader.person.firstName + ' ' + e.leader.person.lastName
            : 'Nepoznato'
        }));

        this.setState({ rows });
      })
      .catch(err => {
        this.setState({
          rows: [
            {
              id: null,
              unitType: 'Prazno',
              founded: 'Prazno',
              unitName: 'Prazno',
              leaderFullName: 'Prazno'
            }
          ]
        });
      });
  }

  render() {
    if (this.state.addNewUnit) {
      return (
        <Redirect
          push
          to={{
            pathname: '/unos-jedinice'
          }}
        />
      );
    }

    if (this.state.showUnitData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/podaci-o-jedinici'
          }}
        />
      );
    }

    return (
      <div>
        <Button onClick={this.addUnit}>Dodaj novu jedinicu</Button>
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="left" width={1}>
                  #
                </TableCell>
                <TableCell align="left">Tip jedinice</TableCell>
                <TableCell align="left">Osnovana</TableCell>
                <TableCell align="left">Naziv</TableCell>
                <TableCell align="left">Vodnik/Predvodnik</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.rows.map((row, index) => (
                <TableRow
                  key={row.id}
                  onClick={() => {
                    this.getUnit(row.id);
                  }}
                  hover>
                  <TableCell align="left">{index + 1}.</TableCell>
                  <TableCell component="th" scope="row">
                    {row.unitType}
                  </TableCell>
                  <TableCell align="left">{row.founded}</TableCell>
                  <TableCell align="left">{row.unitName}</TableCell>
                  <TableCell align="left">{row.leaderFullName}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    );
  }
}

export default UnitList;
