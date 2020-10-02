import React, { Fragment } from 'react';

import clsx from 'clsx';

import { Paper } from '@material-ui/core';

import { connect } from 'react-redux';

const Footer = props => {
  const { footerFixed } = props;
  return (
    <Fragment>
      <Paper
        square
        className={clsx('app-footer text-black-50', {
          'app-footer--fixed': footerFixed
        })}>
        <div className="app-footer--inner">
          <div className="app-footer--second">
            © 2020 - by{' '}
            <a
              href="https://igman92.org"
              rel="noopener noreferrer"
              target="_blank"
              title="www.igman92.org">
              igman92
            </a>
          </div>
        </div>
      </Paper>
    </Fragment>
  );
};

const mapStateToProps = state => ({
  footerFixed: state.ThemeOptions.footerFixed
});
export default connect(mapStateToProps)(Footer);
