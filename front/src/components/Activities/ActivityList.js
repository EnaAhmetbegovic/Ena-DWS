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

class ActivityList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showActivityData: false,
      addNewActivity: false,

      rows: [
        {
          id: null,
          activityType: null,
          name: null,
          startDate: null,
          endDate: null,
          organizer: null
        }
      ]
    };

    this.addActivity = this.addActivity.bind(this);
    this.getActivity = this.getActivity.bind(this);
  }

  addActivity() {
    this.setState({ addNewActivity: true });
  }

  getActivity(activityId) {
    localStorage.setItem('activityId', JSON.stringify(activityId));
    this.setState({ showActivityData: true });
  }

  componentDidMount() {
    let scoutGroup = 5;
    axios
      .get(`http://localhost:8081/activity/by-scout-group/${scoutGroup}/`)
      .then(res => {
        let rows = res.data.map(e => ({
          id: e.id,
          activityType: e.activityType.name,
          name: e.name,
          startDate: moment(e.startDate).format('D. M. YYYY.'),
          endDate: moment(e.endDate).format('D. M. YYYY.'),
          organizer: e.organizer.scoutGroup.name
        }));

        this.setState({ rows });
      })
      .catch(err => {
        this.setState({
          rows: [
            {
              id: null,
              activityType: 'Prazno',
              name: 'Prazno',
              startDate: 'Prazno',
              endDate: 'Prazno',
              organizer: 'Prazno'
            }
          ]
        });
      });
  }

  render() {
    // if (this.state.addNewUnit) {
    //   return (
    //     <Redirect
    //       push
    //       to={{
    //         pathname: '/unos-jedinice'
    //       }}
    //     />
    //   );
    // }
    //
    if (this.state.showActivityData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/podaci-o-aktivnosti'
          }}
        />
      );
    }

    return (
      <div>
        <Button onClick={this.addUnit}>Dodaj novu aktivnost</Button>
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="left" width={1}>
                  #
                </TableCell>
                <TableCell align="left">Tip aktivnosti</TableCell>
                <TableCell align="left">Naziv</TableCell>
                <TableCell align="left">Datum početka</TableCell>
                <TableCell align="left">Datum kraja</TableCell>
                <TableCell align="left">Organizator</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.rows.map((row, index) => (
                <TableRow
                  key={row.id}
                  onClick={() => {
                    this.getActivity(row.id);
                  }}
                  hover>
                  <TableCell align="left">{index + 1}.</TableCell>
                  <TableCell component="th" scope="row">
                    {row.activityType}
                  </TableCell>
                  <TableCell align="left">{row.name}</TableCell>
                  <TableCell align="left">{row.startDate}</TableCell>
                  <TableCell align="left">{row.endDate}</TableCell>
                  <TableCell align="left">{row.organizer}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>
    );
  }
}

export default ActivityList;
