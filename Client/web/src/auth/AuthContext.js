import React, { createContext, useState, useContext } from 'react';
import PropTypes from 'prop-types';

import { WebAuth } from 'auth0-js';
import AUTH_CONFIG from './auth0-variables';

const generateAuth = () => new WebAuth({
    domain: AUTH_CONFIG.domain,
    clientID: AUTH_CONFIG.clientID,
    redirectUri: AUTH_CONFIG.callbackUrl,
    responseType: 'token id_token',
    scope: 'openid',
  });

const Auth0Context = createContext(null);

const useAuthState = () => useState({
    accessToken: null,
    idToken: null,
    expiresAt: 0,
  });

const useContextValue = () => {
  const [authState, updateAuthState] = useAuthState();

  return {
    auth0: generateAuth(),
    authState,
    updateAuthState,
  };
};

export const Auth0Provider = ({ children }) => {
  const value = useContextValue();

  return (
    <Auth0Context.Provider value={value}>{children}</Auth0Context.Provider>
  );
};

Auth0Provider.propTypes = {
  children: PropTypes.node,
};

Auth0Provider.defaultProps = {
  children: null,
};

export const useAuth0Context = () => useContext(Auth0Context);
