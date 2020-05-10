import React, { useEffect } from 'react';
import { Route } from 'react-router-dom';
import PropTypes from 'prop-types';

import { useAuth0 } from 'auth0';

const PrivateRoute = ({ component: Component, path, ...rest }) => {
  const { loading, isAuthenticated, loginWithRedirect } = useAuth0();

  useEffect(() => {
    if (loading || isAuthenticated) {
      return;
    }
    const fn = async () => {
      await loginWithRedirect({
        appState: { targetUrl: window.location.pathname },
      });
    };
    fn();
  }, [loading, isAuthenticated, loginWithRedirect, path]);

  return (
    <Route
      path={path}
      render={(props) => (isAuthenticated === true ? <Component {...props} /> : null)}
      {...rest}
    />
  );
};

PrivateRoute.propTypes = {
  component: PropTypes.func,
  path: PropTypes.string,
};

PrivateRoute.defaultProps = {
  component: null,
  path: '',
};

export default PrivateRoute;
