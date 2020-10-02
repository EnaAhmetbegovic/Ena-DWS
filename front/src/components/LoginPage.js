import React, { Fragment } from 'react';

import { Grid, Container, Button } from '@material-ui/core';

import { Link } from 'react-router-dom';

import main_pozadina from '../assets/images/hero-bg/main_pozadina.jpg';
import Odredski_bez_pozadine from '../assets/images/hero-bg/Odredski_bez_pozadine.png';

const LandingPage = () => {
  return (
    <Fragment>
      <div className="hero-wrapper bg-composed-wrapper bg-premium-dark min-vh-100">
        <img className="amblem" src={Odredski_bez_pozadine} alt={'Amblem'} />
        <div className="divider border-2 border-light my-5 border-light opacity-2 mx-auto rounded-circle w-50" />

        <div className="flex-grow-1 w-100 d-flex align-items-center">
          <div
            className="bg-composed-wrapper--image opacity-5"
            style={{ backgroundImage: 'url(' + main_pozadina + ')' }}
          />

          <div className="bg-composed-wrapper--content pt-5 pb-2 py-lg-5">
            <Container maxWidth="md" className="pb-5">
              <Grid container spacing={4}>
                <Grid
                  item
                  lg={10}
                  className="px-0 mx-auto d-flex align-items-center">
                  <div className="text-center">
                    <div className="px-4 px-sm-0 text-white mt-4">
                      <div>
                        {/*<div className="loginForm">*/}
                        {/*  <div>*/}
                        {/*    <label htmlFor="username">Korisničko ime</label>*/}
                        {/*    <br />*/}
                        {/*    <input*/}
                        {/*      id="username"*/}
                        {/*      name="username"*/}
                        {/*      value=""*/}
                        {/*      type="text"*/}
                        {/*      autoFocus=""*/}
                        {/*    />*/}
                        {/*  </div>*/}
                        {/*  <br />*/}
                        {/*  <div>*/}
                        {/*    <label htmlFor="username">Lozinka</label>*/}
                        {/*    <br />*/}
                        {/*    <input*/}
                        {/*      id="password"*/}
                        {/*      name="password"*/}
                        {/*      value=""*/}
                        {/*      type="text"*/}
                        {/*      autoFocus=""*/}
                        {/*      autoComplete="off"*/}
                        {/*    />*/}
                        {/*  </div>*/}
                        {/*</div>*/}

                        <Button
                          to="/HomePage"
                          component={Link}
                          size="large"
                          color="primary"
                          variant="contained"
                          className="m-1 py-3 px-5"
                          title="Login">
                          <span className="btn-wrapper--label">Nastavi na profil</span>
                          <span className="btn-wrapper--icon"></span>
                        </Button>
                      </div>
                      {/*<small className="d-block pt-4">*/}
                      {/*  <a href="#">Zaboravljena lozinka?</a>*/}
                      {/*</small>*/}
                    </div>
                  </div>
                </Grid>
              </Grid>
            </Container>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default LandingPage;
