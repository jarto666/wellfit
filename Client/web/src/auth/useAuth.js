import { useCallback, useMemo } from 'react';
import history from 'utils/history';
import { useAuth0Context } from './AuthContext';

const useIsAuthenticated = (expiresAt) => useMemo(() => new Date().getTime() < expiresAt, [expiresAt]);

const useAuth0 = () => {
  const { auth0, authState, updateAuthState } = useAuth0Context();

  const isAuthenticated = useIsAuthenticated(authState.expiresAt);

  const login = useCallback(() => {
    auth0.login({
      email: 'korart95+u1@gmail.com',
      password: '1234Asdf',
    });
  }, [auth0]);

  const logout = useCallback(() => {
    updateAuthState({
      accessToken: null,
      idToken: null,
      expiresAt: 0,
    });
    localStorage.removeItem('isLoggedIn');

    auth0.logout({
      returnTo: window.location.origin,
    });

    // navigate to the home route
    history.replace('/home');
  }, [auth0, updateAuthState]);

  const setSession = useCallback(
    (authResult) => {
      localStorage.setItem('isLoggedIn', 'true');

      const expiresAt = authResult.expiresIn * 1000 + new Date().getTime();
      updateAuthState({
        accessToken: authResult.accessToken,
        idToken: authResult.idToken,
        expiresAt,
      });
      history.replace('/home');
    },
    [updateAuthState]
  );

  const renewSession = useCallback(() => {
    auth0.checkSession({}, (err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        setSession(authResult);
      } else if (err) {
        logout();
        console.error(err); // eslint-disable-line no-console
      }
    });
  }, []); // eslint-disable-line

  const handleAuthentication = useCallback(() => {
    auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        setSession(authResult);
      } else if (err) {
        history.replace('/home');
        console.error(err); // eslint-disable-line no-console
      }
    });
  }, []); // eslint-disable-line

  // retun some functions
  return {
    login,
    logout,
    handleAuthentication,
    isAuthenticated,
    renewSession,
  };
};

export default useAuth0;
