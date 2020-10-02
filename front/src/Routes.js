import React, { Suspense } from 'react';
import { Switch, Route, Redirect, useLocation } from 'react-router-dom';
import { AnimatePresence, motion } from 'framer-motion';

import { ThemeProvider } from '@material-ui/styles';

import MuiTheme from './theme';

import { LeftSidebar, PresentationLayout } from './layout-blueprints';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import AddNewMember from './components/Members/AddNewMember';
import MemberList from './components/Members/MemberList';
import MemberData from './components/Members/MemberData';
import EnrollNewMember from './components/Members/EnrollNewMember';
import AddNewUnit from './components/Units/AddNewUnit';
import UnitList from './components/Units/UnitList';
import AddNewActivity from './components/Activities/AddNewActivity';
import ActivityList from './components/Activities/ActivityList';
import UnitData from './components/Units/UnitData';
import ActivityData from './components/Activities/ActivityData';

const Routes = () => {
  const location = useLocation();

  const pageVariants = {
    initial: {
      opacity: 0,
      scale: 0.99
    },
    in: {
      opacity: 1,
      scale: 1
    },
    out: {
      opacity: 0,
      scale: 1.01
    }
  };

  const pageTransition = {
    type: 'tween',
    ease: 'anticipate',
    duration: 0.4
  };

  return (
    <ThemeProvider theme={MuiTheme}>
      <AnimatePresence>
        <Suspense
          fallback={
            <div className="d-flex align-items-center vh-100 justify-content-center text-center font-weight-bold font-size-lg py-3">
              <div className="w-50 mx-auto">Učitava se!</div>
            </div>
          }>
          <Switch>
            <Redirect exact from="/" to="/LoginPage" />
            <Route path={['/LoginPage']}>
              <PresentationLayout>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/LoginPage" component={LoginPage} />
                  </motion.div>
                </Switch>
              </PresentationLayout>
            </Route>

            <Route path={['/HomePage']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/HomePage" component={HomePage} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/unos-clana']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/unos-clana" component={AddNewMember} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/podaci-o-clanu']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/podaci-o-clanu" component={MemberData} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/clanovi']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/clanovi" component={MemberList} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/unos-jedinice']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/unos-jedinice" component={AddNewUnit} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/jedinice']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/jedinice" component={UnitList} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/podaci-o-jedinici']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/podaci-o-jedinici" component={UnitData} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/unos-aktivnosti']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/unos-aktivnosti" component={AddNewActivity} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/aktivnosti']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/aktivnosti" component={ActivityList} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/podaci-o-aktivnosti']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route path="/podaci-o-aktivnosti" component={ActivityData} />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>

            <Route path={['/upisi-novog-clana']}>
              <LeftSidebar>
                <Switch location={location} key={location.pathname}>
                  <motion.div
                    initial="initial"
                    animate="in"
                    exit="out"
                    variants={pageVariants}
                    transition={pageTransition}>
                    <Route
                      path="/upisi-novog-clana"
                      component={EnrollNewMember}
                    />
                  </motion.div>
                </Switch>
              </LeftSidebar>
            </Route>
          </Switch>
        </Suspense>
      </AnimatePresence>
    </ThemeProvider>
  );
};

export default Routes;
