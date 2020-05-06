import React, { useCallback, useEffect } from 'react';
import useAuth0 from 'auth/useAuth';
import PropTypes from 'prop-types';

const useGoToHandler = (history) => useCallback((route) => () => history.replace(`/${route}`), [history]);

const App = ({ history }) => {
  const { login, logout, isAuthenticated, renewSession } = useAuth0();

  const goToHandler = useGoToHandler(history);
  useEffect(() => {
    if (localStorage.getItem('isLoggedIn') === 'true') {
      renewSession();
    }
  }, [renewSession]);

  return (
    <div>
      <button type="button" onClick={goToHandler('home')}>
        Home
      </button>

      {!isAuthenticated && (
        <button type="button" onClick={login}>
          Log In
        </button>
      )}

      {isAuthenticated && (
        <button type="button" onClick={logout}>
          Log Out
        </button>
      )}
    </div>
  );
};

App.propTypes = {
  history: PropTypes.shape({}),
};

App.defaultProps = {
  history: {},
};

export default App;
