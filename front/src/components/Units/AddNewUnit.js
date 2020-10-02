import React, { Component } from 'react';

import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';

import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';

import Box from '@material-ui/core/Box';
import request from '../../utils/request';
import axios from 'axios';
import { Redirect } from 'react-router';

export default class AddNewUnit extends Component {
  constructor(props) {
    super(props);
    this.state = {
      addUnitData: false,

      leaders: {
        leaders: []
      },
      unitTypes: [],
      scoutGroups: [],

      unit: null,
      unitName: null,
      leader: null,
      founded: new Date(),
      scoutGroup: null
    };

    this.addUnit = this.addUnit.bind(this);
    this.handleFoundingDate = this.handleFoundingDate.bind(this);
  }

  addUnit() {
    let obj = {
      unit: {
        id: this.state.unit
      },
      unitName: this.state.unitName,
      leader: {
        id: this.state.leader
      },
      founded: this.state.founded,
      scoutGroup: {
        id: this.state.scoutGroup
      }
    };

    axios.post('http://localhost:8081/unit/add/', { ...obj }).then(res => {
      localStorage.setItem('unitId', JSON.stringify(res.data.id));
      this.setState({ addUnitData: true });
    });
  }

  handleFoundingDate(date) {
    this.setState({ founded: date });
  }

  componentDidMount() {
    request
      .fetchLeadersForScoutGroup(5)
      .then(leaders => this.setState({ leaders }));

    request
      .fetchScoutGroups()
      .then(scoutGroups => this.setState({ scoutGroups }));

    request.fetchUnitTypes().then(unitTypes => this.setState({ unitTypes }));
  }

  render() {
    if (this.state.addUnitData) {
      return (
        <Redirect
          push
          to={{
            pathname: '/jedinice'
          }}
        />
      );
    }

    const { leaders, scoutGroups, unitTypes } = this.state;

    return (
      <div>
        <h4>Dodaj novu jedinicu</h4>
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
                  <InputLabel>Tip jedinice</InputLabel>
                  <Select
                    onChange={e => {
                      this.setState({
                        unit: e.target.value
                      });
                    }}>
                    {unitTypes.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>

                <br />
                <br />

                <TextField
                  id="unitName"
                  label="Naziv jedinice"
                  value={this.state.unitName}
                  onChange={e => {
                    this.setState({ unitName: e.target.value });
                  }}
                />
                <br />
                <br />

                <p>Osnovana: </p>
                <DatePicker
                  selected={this.state.founded}
                  onChange={this.handleFoundingDate}
                  dateFormat="dd/MM/yyyy"
                />
                <br />

                <FormControl required>
                  <InputLabel>Vodnik/Predvodnik</InputLabel>
                  <Select
                    onChange={e => {
                      this.setState({
                        leader: e.target.value
                      });
                    }}>
                    {leaders.leaders.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />

                <FormControl required>
                  <InputLabel>Odred izviđača</InputLabel>
                  <Select
                    onChange={e => {
                      this.setState({
                        scoutGroup: e.target.value
                      });
                    }}>
                    {scoutGroups.map(e => (
                      <MenuItem key={e.value} value={e.value}>
                        {e.text}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
                <br />
                <br />
                <Button onClick={this.addUnit}>Unesi jedinicu</Button>
              </Box>
            </form>
          </Typography>
        </Container>{' '}
      </div>
    );
  }
}
