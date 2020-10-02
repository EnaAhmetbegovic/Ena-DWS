import React from 'react';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import request from '../../utils/request';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import Box from '@material-ui/core/Box';

export default class AddMemberToUnit extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      members: [],
      memberId: null,
      joinedUnit: new Date(),
      leftUnit: new Date()
    };
  }

  componentDidMount() {
    request
      .fetchMembersForUnit(5, JSON.parse(localStorage.getItem('unitId')))
      .then(({ members }) => {
        this.setState({
          members
        });
      })
      .catch(err => console.log(err));

    this.addMember = this.addMember.bind(this);
    this.handleJoinedUnitChange = this.handleJoinedUnitChange.bind(this);
    this.handleLeftUnitChange = this.handleJoinedUnitChange.bind(this);
  }

  handleJoinedUnitChange(date) {
    this.setState({ joinedUnit: date });
  }

  handleLeftUnitChange(date) {
    this.setState({ leftUnit: date });
  }

  addMember() {
    let obj = {
      member: {
        id: this.state.memberId
      },
      unit: {
        id: JSON.parse(localStorage.getItem('unitId'))
      },
      joinedUnit: this.state.joinedUnit,
      leftUnit: this.state.leftUnit
    };

    axios
      .post(`http://localhost:8081/member/add-member-to-unit/`, { ...obj })
      .then(res => {
        window.location.reload(true);
      });
  }

  render() {
    const { members } = this.state;
    return (
      <div>
        <FormControl required>
          <InputLabel>Članovi</InputLabel>
          <Select
            onChange={e => {
              this.setState(
                {
                  memberId: e.target.value
                },
                () => console.log(this.state.memberId)
              );
            }}>
            {members.map(e => (
              <MenuItem key={e.value} value={e.value}>
                {e.text}
              </MenuItem>
            ))}
          </Select>
          <br />

          <DatePicker
            selected={this.state.joinedUnit}
            onChange={this.handleJoinedUnitChange}
            dateFormat="dd/MM/yyyy"
          />
          <br />

          {/*<DatePicker*/}
          {/*  selected={this.state.leftUnit}*/}
          {/*  onChange={this.handleLeftUnitChange}*/}
          {/*  dateFormat="dd/MM/yyyy"*/}
          {/*/>*/}
          <br />
          <Button onClick={this.addMember}>Dodaj člana</Button>
        </FormControl>
        <br />
      </div>
    );
  }
}
