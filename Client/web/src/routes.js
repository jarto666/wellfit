import React from 'react';
import { Route, Router, Switch } from 'react-router-dom';
import App from 'containers/App';
import Home from 'components/Home';
import Callback from 'components/Callback';
import history from 'utils/history';
import { Auth0Provider } from 'auth/AuthContext';

const Routes = () => (
  <Router history={history}>
    <Route path="/" render={(props) => <App {...props} />} />
    <Switch>
      <Route path="/home" render={(props) => <Home {...props} />} />
      <Route path="/callback" render={(props) => <Callback {...props} />} />
    </Switch>
  </Router>
  );

const makeMainRoutes = () => (
  <Auth0Provider>
    <Routes />
  </Auth0Provider>
  );

export default makeMainRoutes;
