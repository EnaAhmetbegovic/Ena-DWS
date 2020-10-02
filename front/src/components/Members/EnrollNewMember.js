import React, { Component } from 'react';
import request from '../../utils/request';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import TextField from '@material-ui/core/TextField';
import DatePicker from 'react-datepicker';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import axios from 'axios';
import { Redirect } from 'react-router';

class EnrollNewMember extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showPersonData: false,
      scoutGroups: [],

      memberId: JSON.parse(localStorage.getItem('memberId')),
      idCardNumber: null,
      recordNumber: null,
      scoutGroupId: null,
      memberSince: new Date()
    };
    this.enrollMember = this.enrollMember.bind(this);
    this.handleMemberSince = this.handleMemberSince.bind(this);
  }

  enrollMember() {
    let obj = {
      member: {
        id: this.state.memberId,
        recordNumber: this.state.recordNumber,
        idCardNumber: this.state.idCardNumber
      },
      scoutGroup: {
        scoutGroup: {
          id: this.state.scoutGroupId
        }
      },
      memberSince: this.state.memberSince
    };

    axios.post('http://localhost:8081/member/enroll-new-member/', { ...obj });

    this.setState({ showPersonData: true });
  }

  handleMemberSince(date) {
    this.setState({ memberSince: date });
  }

  componentDidMount() {
    request
      .fetchScoutGroups()
      .then(scoutGroups => this.setState({ scoutGroups }));
  }

  render() {
    if (this.state.showPersonData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/clanovi'
          }}
        />
      );
    }

    const { scoutGroups } = this.state;

    return (
      <Container maxWidth="sm">
        <Typography
          component="div"
          style={{
            backgroundColor: '#f8f8f7',
            height: '100%',
            padding: '10px'
          }}>
          <form noValidate autoComplete="off">
            <Box>
              <FormControl required>
                <InputLabel>Odred izviđača</InputLabel>
                <Select
                  onChange={e => {
                    this.setState(
                      {
                        scoutGroupId: e.target.value
                      },
                      () => console.log(this.state.scoutGroupId)
                    );
                  }}>
                  {scoutGroups.map(e => (
                    <MenuItem key={e.value} value={e.value}>
                      {e.text}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <br />

              <TextField
                id="idCard"
                label="Broj članske knjižice"
                value={this.state.idCardNumber}
                onChange={e => {
                  this.setState({ idCardNumber: e.target.value });
                }}
              />
              <br />
              <br />

              <TextField
                id="recordNumber"
                label="Redni broj člana"
                value={this.state.recordNumber}
                onChange={e => {
                  this.setState({ recordNumber: e.target.value });
                }}
              />
              <br />
              <br />

              <InputLabel>Član od: </InputLabel>
              <DatePicker
                selected={this.state.memberSince}
                onChange={this.handleMemberSince}
                dateFormat="dd/MM/yyyy"
              />
              <br />

              <Button onClick={this.enrollMember}>Upiši člana</Button>
            </Box>
          </form>
        </Typography>
      </Container>
    );
  }
}

export default EnrollNewMember;
