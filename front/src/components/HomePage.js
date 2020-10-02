import React, { Component, Fragment } from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import axios from 'axios';
import { Grid } from 'semantic-ui-react';

class HomePage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      totalNumberOfMembers: 0,
      numberOfNewMembers: 0,
      numberOfYearlyActivities: 0
    };
  }

  componentDidMount() {
    let scoutGroup = 5;
    axios
      .get(`http://localhost:8081/scout-group/data/${scoutGroup}/`)
      .then(res => {
        this.setState({
          totalNumberOfMembers: res.data.totalNumberOfMembers
        });
        this.setState({ numberOfNewMembers: res.data.numberOfNewMembers });
        this.setState({
          numberOfYearlyActivities: res.data.numberOfYearlyActivities
        });
      });
  }

  render() {
    return (
      <Fragment>
        <Grid container columns={3} centered>
          <Grid item xs={12}>
            <Grid>
              <Card style={{ width: '20%' }}>
                <CardContent>
                  <Typography>Ukupan broj članova</Typography>
                  <Typography>{this.state.totalNumberOfMembers}</Typography>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
          <Grid item xs={12}>
            <Card style={{ width: '20%' }}>
              <CardContent>
                <Typography>Broj novih članova u trenutnoj godini</Typography>
                <Typography>{this.state.numberOfNewMembers}</Typography>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12}>
            <Card style={{ width: '20%' }}>
              <CardContent>
                <Typography>
                  Ukupan broj aktivnosti u trenutnoj godini
                </Typography>
                <Typography>{this.state.numberOfYearlyActivities}</Typography>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      </Fragment>
    );
  }
}

export default HomePage;
