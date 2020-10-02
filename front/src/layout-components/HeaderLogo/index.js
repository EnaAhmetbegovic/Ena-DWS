import React, { Fragment } from 'react';

import clsx from 'clsx';
import { Link } from 'react-router-dom';

import { IconButton, Box } from '@material-ui/core';

import Odredski_bez_pozadine from '../../assets/images/hero-bg/Odredski_bez_pozadine.png';

const HeaderLogo = props => {
  return (
    <Fragment>
      <div className={clsx('app-header-logo', {})}>
        <Box className="header-logo-wrapper" title="Home">
          <Link to="/HomePage" className="header-logo-wrapper-link">
            <IconButton
              color="primary"
              size="medium"
              className="header-logo-wrapper-btn">
              <img
                className="app-header-logo-img"
                alt="Odredski amblem"
                src={Odredski_bez_pozadine}
              />
            </IconButton>
          </Link>
          <Box className="header-logo-text">Igman 92</Box>
        </Box>
      </div>
    </Fragment>
  );
};

export default HeaderLogo;
