import React from 'react';
import ReactDOM from 'react-dom';
import App from 'containers/App';
import * as serviceWorker from 'serviceWorker';
import { Auth0Provider } from 'utils/auth/auth0';
import config from 'utils/auth/auth_config.json';
import history from 'utils/history';

import 'sanitize.css';
import './index.css';

// A function that routes the user to the right place
// after login
const onRedirectCallback = (appState) => {
  history.push(
    appState && appState.targetUrl
      ? appState.targetUrl
      : window.location.pathname
  );
};

ReactDOM.render(
  <Auth0Provider
    domain={config.domain}
    client_id={config.clientId}
    redirect_uri={window.location.origin}
    onRedirectCallback={onRedirectCallback}
  >
    <App />
  </Auth0Provider>,
  document.getElementById('root')
);

serviceWorker.unregister();
