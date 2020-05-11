import React from 'react';
import { useAuth0 } from 'utils/auth/auth0';
import { Link } from 'react-router-dom';

import styles from './index.module.css';

const NavBar = () => {
  const { isAuthenticated, loginWithRedirect, logout } = useAuth0();

  return (
    <div className={styles.container}>
      {!isAuthenticated && (
        <button type="button" onClick={() => loginWithRedirect({})}>Log in</button>
      )}

      {isAuthenticated && <button type="button" onClick={() => logout()}>Log out</button>}

      {isAuthenticated && (
        <span>
          <Link to="/">Home</Link>&nbsp;
          <Link to="/profile">Profile</Link>
        </span>
      )}
    </div>
  );
};

export default NavBar;
