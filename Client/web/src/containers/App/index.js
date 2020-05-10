import React from 'react';
import NavBar from 'components/NavBar';

import { Router, Route, Switch } from 'react-router-dom';
import Profile from 'components/Profile';
import PrivateRoute from 'components/PrivateRoute';
import history from 'utils/history';

const App = () => (
  <div className="App">
    <Router history={history}>
      <header>
        <NavBar />
      </header>

      <Switch>
        <Route path="/" exact />

        <PrivateRoute path="/profile" component={Profile} />
      </Switch>
    </Router>
  </div>
);

export default App;
