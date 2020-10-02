import React, { Component } from 'react';

import axios from 'axios';
import { Grid, Header, Segment } from 'semantic-ui-react';

import ActivityBasicInfoTable from './ActivityInfo/ActivityBasicInfoTable';
import Button from '@material-ui/core/Button';
import ActivityMembers from './ActivityInfo/ActivityMembers';
import AddMemberToActivity from './AddMemberToActivity';

class ActivityData extends Component {
  constructor(props) {
    super(props);
    this.state = {
      activity: {
        activityType: {
          name: null
        },
        name: null,
        startDate: null,
        endDate: null,
        place: null,
        head: {
          person: {
            firstName: null,
            lastName: null
          }
        },
        chief: {
          person: {
            firstName: null,
            lastName: null
          }
        },
        organizer: {
          scoutGroup: {
            name: null
          }
        }
      },
      memberActivities: [],
      showInputForm: false
    };

    this.addMember = this.addMember.bind(this);
  }

  addMember() {
    this.setState({ showInputForm: true });
  }

  componentDidMount() {
    let activityId = JSON.parse(localStorage.getItem('activityId'));

    axios.get(`http://localhost:8081/activity/${activityId}/`).then(res => {
      console.log(res.data);
      this.setState({ activity: res.data.activity });
      this.setState({ memberActivities: res.data.memberActivities });
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
                <Header as="h3">Informacije o aktivnosti</Header>
              </Segment>
              <Segment textAlign="center">
                <ActivityBasicInfoTable
                  activityInfoData={this.state.activity}
                />
              </Segment>
            </Segment.Group>
          </Grid.Column>
        </Grid.Row>

        <br />

        <Grid.Row stretched>
          <Grid.Column>
            <Segment.Group>
              <Segment>
                <Button onClick={this.addMember}>Dodaj novog učesnika</Button>
                {showInputForm && <AddMemberToActivity />}
                <Header as="h3">Učesnici</Header>
              </Segment>
              <Segment textAlign="center">
                <ActivityMembers
                  activityInfoData={this.state.memberActivities}
                />
              </Segment>
            </Segment.Group>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    );
  }
}

export default ActivityData;
