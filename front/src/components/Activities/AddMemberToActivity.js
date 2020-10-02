import React from 'react';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import request from '../../utils/request';
import Button from '@material-ui/core/Button';
import axios from 'axios';
import TextField from '@material-ui/core/TextField';

export default class AddMemberToActivity extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      members: [],
      memberId: null,
      comment: null
    };
  }

  componentDidMount() {
    request
      .fetchMembersForUnit(5, JSON.parse(localStorage.getItem('activityId')))
      .then(({ members }) => {
        this.setState({
          members
        });
      })
      .catch(err => console.log(err));

    this.addMember = this.addMember.bind(this);
  }

  addMember() {
    let obj = {
      member: {
        id: this.state.memberId
      },
      activity: {
        id: JSON.parse(localStorage.getItem('activityId'))
      },
      comment: this.state.comment
    };

    axios
      .post(`http://localhost:8081/member/add-member-to-activity/`, { ...obj })
      .then(res => {
        window.location.reload(true);
      });
  }

  render() {
    const { members } = this.state;
    return (
      <div>
        <FormControl required>
          <InputLabel>Učesnici</InputLabel>
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

          <TextField
            id="comment"
            label="Komentar"
            value={this.state.comment}
            onChange={e => {
              this.setState({ comment: e.target.value });
              console.log(this.state.comment);
            }}
          />
          <br />

          <br />
          <Button onClick={this.addMember}>Dodaj učesnika</Button>
        </FormControl>
        <br />
      </div>
    );
  }
}
