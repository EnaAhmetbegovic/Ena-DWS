import React, { Component } from 'react';

import { Header, Grid, Segment } from 'semantic-ui-react';
import PersonBasicInfoTable from './MemberInfo/PersonBasicInfoTable';
import request from '../../utils/request';
import MemberBasicInfoTable from './MemberInfo/MemberBasicInfoTable';
import MemberActivities from './MemberInfo/MemberActivities';
import MemberUnits from './MemberInfo/MemberUnits';

class MemberData extends Component {
  constructor(props) {
    super(props);
    this.state = {
      personData: {
        personData: {
          personDTO: {
            personId: null,
            firstName: null,
            lastName: null,
            gender: {
              name: null
            },
            jmbg: null,
            fathersFullName: null,
            mothersFullName: null,
            dateOfBirth: null,
            placeOfBirth: {
              name: null,
              municipality: {
                name: null
              },
              canton: {
                name: null
              },
              entity: {
                name: null
              },
              country: {
                name: null
              }
            },
            nationality: {
              name: null
            },
            residence: {
              street: null,
              place: {
                name: null,
                municipality: {
                  name: null
                },
                canton: {
                  name: null
                },
                entity: {
                  name: null
                },
                country: {
                  name: null
                }
              }
            },
            bloodType: {
              name: null
            }
          },
          memberId: null,
          idCardNumber: null,
          recordNumber: null,
          scoutGroup: {
            scoutGroup: {
              name: null
            }
          },
          memberSince: null,
          units: [],
          category: {
            name: null
          },
          activities: []
        }
      }
    };
  }

  componentDidMount() {
    let memberId = JSON.parse(localStorage.getItem('memberId'));
    request.fetchPersonInfo(memberId).then(personData => {
      this.setState({ personData: personData });
    });
  }

  render() {
    const { personData } = this.state;

    return (
      <div>
        <Grid columns={2} className="stackable" centered>
          <Grid.Row stretched>
            <Grid.Column>
              <Segment.Group>
                <Segment>
                  <Header as="h3">Osnovne informacije</Header>
                </Segment>
                <Segment textAlign="center">
                  <PersonBasicInfoTable personInfoData={personData} />
                </Segment>
              </Segment.Group>
            </Grid.Column>
          </Grid.Row>

          <br />

          <Grid.Row stretched>
            <Grid.Column>
              <Segment.Group>
                <Segment>
                  <Header as="h3">Podaci o članstvu</Header>
                </Segment>
                <Segment textAlign="center">
                  <MemberBasicInfoTable personInfoData={personData} />
                </Segment>
              </Segment.Group>
            </Grid.Column>
          </Grid.Row>

          <br />

          <Grid.Row stretched>
            <Grid.Column>
              <Segment.Group>
                <Segment>
                  <Header as="h3">Aktivnosti</Header>
                </Segment>
                <Segment textAlign="center">
                  <MemberActivities personInfoData={personData} />
                </Segment>
              </Segment.Group>
            </Grid.Column>
          </Grid.Row>

          <br />

          <Grid.Row stretched>
            <Grid.Column>
              <Segment.Group>
                <Segment>
                  <Header as="h3">Jedinice</Header>
                </Segment>
                <Segment textAlign="center">
                  <MemberUnits personInfoData={personData} />
                </Segment>
              </Segment.Group>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </div>
    );
  }
}

export default MemberData;
