import React, { Component } from 'react';

import axios from 'axios';
import { Grid, Header, Segment } from 'semantic-ui-react';

import UnitMembers from './UnitInfo/UnitMembers';
import UnitBasicInfoTable from './UnitInfo/UnitBasicInfoTable';
import Button from '@material-ui/core/Button';
import AddMemberToUnit from './AddMemberToUnit';

class UnitData extends Component {
  constructor(props) {
    super(props);
    this.state = {
      unit: {
        unit: {
          name: null
        },
        unitName: null,
        leader: {
          person: {
            firstName: null,
            lastName: null
          }
        },
        founded: new Date(),
        scoutGroup: null
      },
      memberUnits: [],
      showInputForm: false
    };

    this.addMember = this.addMember.bind(this);
  }

  addMember() {
    this.setState({ showInputForm: true });
  }

  componentDidMount() {
    let unitId = JSON.parse(localStorage.getItem('unitId'));

    axios.get(`http://localhost:8081/unit/${unitId}/`).then(res => {
      console.log(res.data);
      this.setState({ unit: res.data.unit });
      this.setState({ memberUnits: res.data.memberUnits });
    });
  }

  render() {
    const { showInputForm } = this.state;
    return (
      <Grid columns={2} className="stackable" centered>
        <Grid.Row stretched>
          <Grid.Column>
            <Segment.Group>
              <Segment>
                <Header as="h3">Informacije o jedinici</Header>
              </Segment>
              <Segment textAlign="center">
                <UnitBasicInfoTable unitInfoData={this.state.unit} />
              </Segment>
            </Segment.Group>
          </Grid.Column>
        </Grid.Row>

        <br />

        <Grid.Row stretched>
          <Grid.Column>
            <Segment.Group>
              <Segment>
                <Button onClick={this.addMember}>Dodaj novog člana</Button>
                {showInputForm && <AddMemberToUnit />}
                <Header as="h3">Članovi</Header>
              </Segment>
              <Segment textAlign="center">
                <UnitMembers unitInfoData={this.state.memberUnits} />
              </Segment>
            </Segment.Group>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    );
  }
}

export default UnitData;
